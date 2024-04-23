package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Ville;

import java.util.Collection;

public interface VilleService {
    Ville saveVille(Ville ville);
    Ville updateVille(Ville ville);
    void deleteVille(Ville ville);
    Ville getVille(Long id);
    Collection<Ville> getAllVilles();
}
