package org.etutoria.backend_android.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.RoleRepository;
import org.etutoria.backend_android.entities.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
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



    public CinemaInitServiceImpl(
            VilleService villeService,
            CinemaService cinemaService,
            SalleService salleService,
            PlaceService placeService,
            CategorieService categorieService,
            FilmService filmService,
            ProjectionService projectionService,
            TicketService ticketService,
            SeanceService seanceService,
            RoleRepository roleService,
            UserService userService
           ) {
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

    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Rabat", "Marrakech", "Tanger","Fez","Tinghir").forEach(nameVille -> {
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeService.saveVille(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeService.getAllVilles().forEach(v -> {
            Stream.of("Megarama", "Megarama", "Megarama", "Megarama", "Megarama").forEach(nameCinema -> {
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                cinema.setVille(v);
                cinema.setNombreSalles(3 + (int) (Math.random() * 7));

                cinemaService.saveCinema(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaService.getAllCinemas().forEach(c -> {
            for (int i = 0; i < c.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle " + (i + 1));
                salle.setCinema(c);
                salle.setNombrePlaces(15 + (int) (Math.random() * 20));
                salleService.saveSalle(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleService.getAllSalles().forEach(s -> {
            for (int i = 0; i < s.getNombrePlaces(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setSalle(s);
                placeService.savePlace(place);
            }
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[]{1, 1.5, 2, 2.5, 3};
        categorieService.getAllCategories().forEach(cat -> {
            Stream.of("Game of Thrones", "Seigneur des Anneaux", "Spiderman").forEach(titreFilm -> {
                Film film = new Film();
                film.setTitre(titreFilm);
                film.setDuree(durees[new Random().nextInt(durees.length)]);

                // Generate a random release date within the last 10 years
                long minDate = System.currentTimeMillis() - (10L * 365 * 24 * 60 * 60 * 1000); // 10 years in milliseconds
                long maxDate = System.currentTimeMillis();
                film.setDateSortie(new Date(minDate + (long) (Math.random() * (maxDate - minDate))));

                // Generate random description and director
                film.setDescription(generateRandomDescription()); // Replace with your logic
                film.setRealisateur(generateRandomDirector()); // Replace with your logic

                film.setPhoto(titreFilm + ".jpeg");
                film.setCategory(cat);
                filmService.saveFilm(film);
            });
        });
    }

    private String generateRandomDirector() {
        return "Director " + (new Random().nextInt(10) + 1);
    }

    private String generateRandomDescription() {
        return "Description " + (new Random().nextInt(10) + 1);
    }

    @Override
    public void initProjections() {
        // implémentation
        double[] prices = new double[]{30, 50, 60, 70, 90, 100};
        String[] languages = new String[]{"English", "French", "Spanish", "German", "Italian", "Japanese"};

        List<Film> films = (List<Film>) filmService.getAllFilms();
        villeService.getAllVilles().forEach(v -> {
            v.getCinemas().forEach(c -> {
                c.getSalles().forEach(s -> {
                    int index = new Random().nextInt(films.size());
                    Film film = films.get(index);
                    seanceService.getAllSeances().forEach(seance -> {
                        Projection projection = new Projection();
                        projection.setDateProjection(new Date());
                        projection.setFilm(film);
                        projection.setPrix(prices[new Random().nextInt(prices.length)]);
                        projection.setSalle(s);
                        projection.setSeance(seance);

                        // Sélection aléatoire de la langue
                        String language = languages[new Random().nextInt(languages.length)];
                        projection.setLangue(language);

                        projectionService.saveProjection(projection);
                    });
                });
            });
        });
    }


    @Override
    public void initTickets() {
        User user = userService.getUserByUsername("user");

        if (user != null) {
            projectionService.getAllProjections().forEach(p -> {
                p.getSalle().getPlaces().forEach(place -> {
                    Ticket ticket = new Ticket();
                    ticket.setPlace(place);
                    ticket.setPrix(p.getPrix());
                    ticket.setProjection(p);
                    ticket.setReserve(false);
                    ticket.setUser(user);

                    // Générer un code de paiement unique en utilisant UUID
                    ticket.setCodePayment(UUID.randomUUID().toString());


                    ticketService.saveTicket(ticket);
                });
            });
        } else {
            System.out.println("User with username 'user' not found. Skipping ticket creation.");
        }
    }










    @Override
    public void initSeances() {
        // implémentation
        DateFormat df  = new java.text.SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(df.parse(s));
                seanceService.saveSeance(seance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initRoles() {
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleService.save(role);

        Role role2 = new Role();
        role2.setRoleName("USER");
        roleService.save(role2);



    }

   /* @PostConstruct
    @Override
    public void initUsers() {
     //faites cette methode en corrigeant les erreurs
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleService.save(role);

        Role role2 = new Role();
        role2.setRoleName("USER");
        roleService.save(role2);

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("1234");
        user.setEmail("admin@gmail.com");
        user.setRoles(List.of(role, role2));
      //corriger cette ligne
        userService.saveNewUser(user);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user");
        user2.setPassword("1234");
        user2.setEmail("user@gmail.com");
        user2.setRoles(List.of(role2));
        userService.saveNewUser(user2);

    }
*/





    @Override
    public void initCategories() {
        cinemaService.getAllCinemas().forEach(c -> {
            Stream.of("Histoire", "Action", "Fiction", "Drama").forEach(cat -> {
                Category categorie = new Category();
                categorie.setName(cat);
                categorieService.saveCategory(categorie);
            });
        });
    }


}


