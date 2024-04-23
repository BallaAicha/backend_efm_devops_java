package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Collection<Cinema> findCinemasByVille(Ville ville);
}
