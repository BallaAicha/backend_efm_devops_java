package org.etutoria.backend_android.service;

import org.etutoria.backend_android.entities.Film;

import java.util.Collection;

public interface FilmService {
    Film saveFilm(Film film);
    Film updateFilm(Film film);
    void deleteFilm(Film film);
    Film getFilm(Long id);
    Collection<Film> getAllFilms();

}
