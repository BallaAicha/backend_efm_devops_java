package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
