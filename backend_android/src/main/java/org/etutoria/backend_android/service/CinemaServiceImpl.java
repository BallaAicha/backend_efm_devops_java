package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.CinemaRepository;
import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Ville;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService{
    private CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        return  cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public void deleteCinema(Cinema cinema) {
        cinemaRepository.delete(cinema);

    }

    @Override
    public Cinema getCinema(Long id) {
        return cinemaRepository.findById(id).get();
    }

    @Override
    public Collection<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Collection<Cinema> findCinemasByVille(Ville ville) {
        return cinemaRepository.findCinemasByVille(ville);
    }


}
