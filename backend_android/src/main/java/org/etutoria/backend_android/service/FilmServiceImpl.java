package org.etutoria.backend_android.service;

import jakarta.transaction.Transactional;
import org.etutoria.backend_android.dao.FilmRepository;
import org.etutoria.backend_android.entities.Film;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class FilmServiceImpl implements FilmService{
    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film saveFilm(Film film) {
        return  filmRepository.save(film);
    }

    @Override
    public Film updateFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Film film) {
        filmRepository.delete(film);

    }

    @Override
    public Film getFilm(Long id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public Collection<Film> getAllFilms() {
        return filmRepository.findAll();
    }
}
