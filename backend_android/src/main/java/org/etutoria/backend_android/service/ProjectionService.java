package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Film;
import org.etutoria.backend_android.entities.Projection;
import org.etutoria.backend_android.entities.Salle;

import java.util.Collection;

public interface ProjectionService {
    Projection saveProjection(Projection projection);
    Projection updateProjection(Projection projection);
    void deleteProjection(Projection projection);
    Projection getProjection(Long id, String projection);
    Iterable<Projection> getAllProjections();


    Collection<Projection> findProjectionsBySalle(Salle salle);

    Collection<Projection> findProjectionsByFilm(Film film);
}
