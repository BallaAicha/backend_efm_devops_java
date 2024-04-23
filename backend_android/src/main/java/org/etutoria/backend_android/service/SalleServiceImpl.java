package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.SalleRepository;
import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Salle;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class SalleServiceImpl implements SalleService{
    private SalleRepository salleRepository;

    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Override
    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public Salle updateSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public void deleteSalle(Salle salle) {
        salleRepository.delete(salle);

    }

    @Override
    public Salle getSalle(Long id) {
        return salleRepository.findById(id).get();
    }

    @Override
    public Collection<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    @Override
    public Collection<Salle> findSallesByCinema(Cinema cinema) {
        return salleRepository.findSallesByCinema(cinema);
    }
}
