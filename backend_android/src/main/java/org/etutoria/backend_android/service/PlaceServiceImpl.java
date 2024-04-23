package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.PlaceRepository;
import org.etutoria.backend_android.entities.Place;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{
    private PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place updatePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public void deletePlace(Place place) {
        placeRepository.delete(place);

    }

    @Override
    public Place getPlace(Long id) {
        return placeRepository.findById(id).get();
    }

    @Override
    public Iterable<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
