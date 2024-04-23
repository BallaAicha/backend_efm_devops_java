package org.etutoria.backend_android;

import org.etutoria.backend_android.service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BackendAndroidApplication implements CommandLineRunner {
    private ICinemaInitService iCinemaInitService;



    public BackendAndroidApplication(ICinemaInitService iCinemaInitService) {
        this.iCinemaInitService = iCinemaInitService;
    }


    public static void main(String[] args) {
        SpringApplication.run(BackendAndroidApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initRoles();
        //iCinemaInitService.initUsers();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();
    }

}
