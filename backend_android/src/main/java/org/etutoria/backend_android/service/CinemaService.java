package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Ville;

import java.util.Collection;

public interface CinemaService {
    Cinema saveCinema(Cinema cinema);
    Cinema updateCinema(Cinema cinema);
    void deleteCinema(Cinema cinema);
    Cinema getCinema(Long id);
    Collection<Cinema> getAllCinemas();

    Collection<Cinema> findCinemasByVille(Ville ville);
}
