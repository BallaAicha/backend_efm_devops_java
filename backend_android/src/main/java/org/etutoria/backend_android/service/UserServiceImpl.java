package org.etutoria.backend_android.service;

import org.etutoria.backend_android.service.exceptions.EmailAlreadyExistsException;
import org.etutoria.backend_android.service.exceptions.ExpiredTokenException;
import org.etutoria.backend_android.service.exceptions.InvalidTokenException;
import org.etutoria.backend_android.service.register.RegistrationRequest;

import org.etutoria.backend_android.service.register.VerificationToken;
import org.etutoria.backend_android.service.register.VerificationTokenRepository;
import org.etutoria.backend_android.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.etutoria.backend_android.dao.RoleRepository;
import org.etutoria.backend_android.dao.UserRepository;
import org.etutoria.backend_android.entities.Role;
import org.etutoria.backend_android.entities.User;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepo;
    @Autowired
    EmailSender emailSender;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;


    }

    @Override
    public User saveNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);

    }

    @Override
    public User getUser(Long id) {
       if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role saveNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }




    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username);
        Role r = roleRepository.findByRoleName(rolename);
        usr.getRoles().add(r); return usr;
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> optionaluser = userRepository.findByEmail(request.getEmail());
        if(optionaluser.isPresent())
            throw new EmailAlreadyExistsException("Email déjà existant!");
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        newUser.setEnabled(false);
        userRepository.save(newUser);//desactiver le compte par defaut avant de valider l'email
        //ajouter à newUser le role par défaut USER
        Role r = roleRepository.findByRoleName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(r);
        newUser.setRoles(roles);



        //génére le code secret
        String code = this.generateCode();

        VerificationToken token = new VerificationToken(code, newUser);
        verificationTokenRepo.save(token);
        //envoyer par email pour valider l'email de l'utilisateur
        sendEmailUser(newUser,token.getToken());
        return userRepository.save(newUser);
    }

    @Override
    public User validateToken(String code) {
        VerificationToken token = verificationTokenRepo.findByToken(code);
        if(token == null){
            throw new InvalidTokenException("Invalid Token");
        }

        User user = token.getUser();//activer le compte
        Calendar calendar = Calendar.getInstance();//calendar est une instance de la classe Calendar pour obtenir la date actuelle
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){//si le token est expiré
            verificationTokenRepo.delete(token);
            throw new ExpiredTokenException("expired Token");
        }
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }


    public String generateCode() {
        Random random = new Random();
        Integer code = 100000 + random.nextInt(900000);

        return code.toString();
    }
@Override
    public void sendEmailUser(User u, String code) {
        String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
                " Votre code de validation est "+"<h1>"+code+"</h1>";
        emailSender.sendEmail(u.getEmail(), emailBody);
    }
}