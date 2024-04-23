package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Collection<Salle> findSallesByCinema(Cinema cinema);
}
