package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Long> {
}
