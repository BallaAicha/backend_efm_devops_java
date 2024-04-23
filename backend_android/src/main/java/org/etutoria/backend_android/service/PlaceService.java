package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Place;

public interface PlaceService {
    Place savePlace(Place place);
    Place updatePlace(Place place);
    void deletePlace(Place place);
    Place getPlace(Long id);
    Iterable<Place> getAllPlaces();

}
