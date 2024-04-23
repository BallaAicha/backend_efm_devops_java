package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
