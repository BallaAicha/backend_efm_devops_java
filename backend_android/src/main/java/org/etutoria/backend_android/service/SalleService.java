package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Salle;

import java.util.Collection;

public interface SalleService {
    Salle saveSalle(Salle salle);
    Salle updateSalle(Salle salle);
    void deleteSalle(Salle salle);
    Salle getSalle(Long id);
    Collection<Salle> getAllSalles();

    Collection<Salle> findSallesByCinema(Cinema cinema);
}
