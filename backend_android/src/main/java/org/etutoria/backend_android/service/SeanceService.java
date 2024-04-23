package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Seance;

public interface SeanceService {
    Seance saveSeance(Seance seance);
    Seance updateSeance(Seance seance);
    void deleteSeance(Seance seance);
    Seance getSeance(Long id);
    Iterable<Seance> getAllSeances();
}
