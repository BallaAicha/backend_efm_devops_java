package org.etutoria.backend_android.web;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.ToString;
import org.etutoria.backend_android.dao.RoleRepository;
import org.etutoria.backend_android.entities.*;
import org.etutoria.backend_android.service.*;
import org.etutoria.backend_android.service.register.RegistrationRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/cinema")
public class CinemaRestController {
    private VilleService villeService;
    private CinemaService cinemaService;
    private SalleService salleService;
    private PlaceService placeService;
    private CategorieService categorieService;
    private FilmService filmService;
    private ProjectionService projectionService;
    private TicketService ticketService;
    private SeanceService seanceService;
    private RoleRepository roleService;
    private UserService userService;

    public CinemaRestController(VilleService villeService, CinemaService cinemaService, SalleService salleService, PlaceService placeService, CategorieService categorieService, FilmService filmService, ProjectionService projectionService, TicketService ticketService, SeanceService seanceService, RoleRepository roleService, UserService userService) {
        this.villeService = villeService;
        this.cinemaService = cinemaService;
        this.salleService = salleService;
        this.placeService = placeService;
        this.categorieService = categorieService;
        this.filmService = filmService;
        this.projectionService = projectionService;
        this.ticketService = ticketService;
        this.seanceService = seanceService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/villes")
    public Collection<Ville> villes() {
            return villeService.getAllVilles();
        }
    @GetMapping("/cinemas")
    public Collection<Cinema> cinemas() {
        return cinemaService.getAllCinemas();
    }
    //obtenir les  villes/1/cinemas
    @GetMapping("/villes/{id}/cinemas")
    public Collection<Cinema> cinemas(@PathVariable Long id) {
        return cinemaService.findCinemasByVille(villeService.getVille(id));
    }
    //cinemas/1
    @GetMapping("/cinemas/{id}")
    public Cinema cinema(@PathVariable Long id) {
        return cinemaService.getCinema(id);
    }
    //cinemas/1/salles
    @GetMapping("/cinemas/{id}/salles")
    public Collection<Salle> salles(@PathVariable Long id) {
        return salleService.findSallesByCinema(cinemaService.getCinema(id));
    }


    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequest request)
    {
        return userService.registerUser(request);
    }

    //pour valider le token
    @GetMapping("/verifyEmail/{token}")
    public User verifyEmail(@PathVariable("token") String token){
        return userService.validateToken(token);
    }



    @GetMapping("/salles")
    public Collection<Salle> salles() {
        return salleService.getAllSalles();
    }
    @GetMapping("/salles/{id}")
    public Salle salle(@PathVariable Long id) {
        return salleService.getSalle(id);
    }
    //salles/1/projections
    @GetMapping("/salles/{id}/projections")
    public Collection<Projection> projections(@PathVariable Long id) {
        return projectionService.findProjectionsBySalle(salleService.getSalle(id));
    }

    //salles/1/projections?projection=p1
    @GetMapping("/salles/{id}/projections/{projection}")
    public Projection projection(@PathVariable Long id, @PathVariable String projection) {
        return projectionService.getProjection(id, projection);
    }

    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") long id) throws Exception{
        Film film = filmService.getFilm(id);
        String PhotoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + PhotoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    //add image
    @PostMapping(path = "/uploadPhoto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadPhoto(@PathVariable(name = "id") long id, @RequestParam(name = "file") MultipartFile file) throws Exception{
        Film film = filmService.getFilm(id);
        String PhotoName = film.getId() + ".jpg";
        Path path = Paths.get(System.getProperty("user.home") + "/cinema/images/" + PhotoName);
        Files.write(path, file.getBytes());
        film.setPhoto(PhotoName);
        filmService.updateFilm(film);
    }

//pour les tickets



    @GetMapping("/films")
    public Collection<Film> films() {
        return filmService.getAllFilms();
    }

    @GetMapping("/films/{id}")
    public Film film(@PathVariable Long id) {
        return filmService.getFilm(id);
    }



    @GetMapping("/tickets")
    public Collection<Ticket> tickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public Ticket ticket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }


    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @GetMapping("/allUsers")
    public Collection<User> users() {
        return userService.getAllUsers();
    }
    //add user
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveNewUser(user);
    }

    @GetMapping("/users/{id}")
    public User user(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/roles")
    public Collection<Role> roles() {
        return roleService.findAll();
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> listTicket = new ArrayList<>();
        User user = userService.getUser(ticketForm.getUser().getId());


        if (user != null) {
            ticketForm.getTickets().forEach(ticketInfo -> {
                Ticket ticket = ticketService.getTicket(ticketInfo.getId());
                //ticket.setNomClient(ticketInfo.getNomClient());
                ticket.setReserve(true);
                ticket.setCodePayment(ticketInfo.getCodePayment());
                ticket.setUser(user);
                ticketService.saveTicket(ticket);
                listTicket.add(ticket);

            });
        } else {
            System.out.println("User not found. Unable to process ticket payment.");
        }

        return listTicket;
    }








}
