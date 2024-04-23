package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Film;
import org.etutoria.backend_android.entities.Projection;
import org.etutoria.backend_android.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProjectionRepository extends JpaRepository<Projection, Long> {
    Collection<Projection> findProjectionsBySalle(Salle salle);

    Collection<Projection> findProjectionsByFilm(Film film);
}
