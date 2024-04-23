package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Role;
import org.etutoria.backend_android.entities.User;
import org.etutoria.backend_android.service.register.RegistrationRequest;

import java.util.Collection;

public interface UserService {
    User saveNewUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    User getUser(Long id);
    Collection<User> getAllUsers();
    User getUserByUsername(String username);
    Role saveNewRole(Role role);
    Role findRoleByRoleName(String roleName);
    //on suppose que username est unique
    User addRoleToUser(String username, String roleName);

    User registerUser(RegistrationRequest request) ;
    public User validateToken(String code);
    public void sendEmailUser(User u, String code);
}
