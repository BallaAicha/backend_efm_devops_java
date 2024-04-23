package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.VilleRepository;
import org.etutoria.backend_android.entities.Ville;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class VilleServiceImpl implements VilleService{
    private VilleRepository villeRepository;

    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Override
    public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public Ville updateVille(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public void deleteVille(Ville ville) {
        villeRepository.delete(ville);

    }

    @Override
    public Ville getVille(Long id) {
        return villeRepository.findById(id).get();
    }

    @Override
    public Collection<Ville> getAllVilles() {
        return villeRepository.findAll();
    }
}
