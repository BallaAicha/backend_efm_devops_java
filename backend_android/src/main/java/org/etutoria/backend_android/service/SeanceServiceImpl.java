package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.SeanceRepository;
import org.etutoria.backend_android.entities.Seance;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SeanceServiceImpl implements SeanceService{
    private SeanceRepository seanceRepository;

    public SeanceServiceImpl(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    @Override
    public Seance saveSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public Seance updateSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public void deleteSeance(Seance seance) {
        seanceRepository.delete(seance);

    }

    @Override
    public Seance getSeance(Long id) {
        return seanceRepository.findById(id).get();
    }

    @Override
    public Iterable<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }
}
