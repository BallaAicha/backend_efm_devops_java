package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.ProjectionRepository;
import org.etutoria.backend_android.entities.Film;
import org.etutoria.backend_android.entities.Projection;
import org.etutoria.backend_android.entities.Salle;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ProjectionServiceImpl  implements ProjectionService{
    private ProjectionRepository projectionRepository;

    public ProjectionServiceImpl(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public Projection saveProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    @Override
    public Projection updateProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    @Override
    public void deleteProjection(Projection projection) {
        projectionRepository.delete(projection);

    }

    @Override
    public Projection getProjection(Long id, String projection) {
        return projectionRepository.findById(id).get();
    }

    @Override
    public Iterable<Projection> getAllProjections() {
        return projectionRepository.findAll();
    }

    @Override
    public Collection<Projection> findProjectionsBySalle(Salle salle) {
        return projectionRepository.findProjectionsBySalle(salle);
    }

    @Override
    public Collection<Projection> findProjectionsByFilm(Film film) {
        return projectionRepository.findProjectionsByFilm(film);
    }
}
