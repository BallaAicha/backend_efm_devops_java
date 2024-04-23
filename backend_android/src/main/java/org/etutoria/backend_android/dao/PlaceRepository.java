package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
