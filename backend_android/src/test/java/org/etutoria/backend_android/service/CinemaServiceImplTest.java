package org.etutoria.backend_android.service;

import org.etutoria.backend_android.dao.CinemaRepository;
import org.etutoria.backend_android.entities.Cinema;
import org.etutoria.backend_android.entities.Ville;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CinemaServiceImplTest {

    @Mock
    private CinemaRepository cinemaRepository;

    private CinemaServiceImpl cinemaService;

    private Cinema cinema;

    @BeforeEach
    public void setUp() {
        cinema = new Cinema();
        cinema.setId(1L);
        cinemaService = new CinemaServiceImpl(cinemaRepository);
    }

    @Test
    public void testSaveCinema() {
        when(cinemaRepository.save(cinema)).thenReturn(cinema);

        Cinema result = cinemaService.saveCinema(cinema);

        assertEquals(cinema, result);
        verify(cinemaRepository, times(1)).save(cinema);
    }

    @Test
    public void testUpdateCinema() {
        when(cinemaRepository.save(cinema)).thenReturn(cinema);

        Cinema result = cinemaService.updateCinema(cinema);

        assertEquals(cinema, result);
        verify(cinemaRepository, times(1)).save(cinema);
    }

    @Test
    public void testDeleteCinema() {
        doNothing().when(cinemaRepository).delete(cinema);

        cinemaService.deleteCinema(cinema);

        verify(cinemaRepository, times(1)).delete(cinema);
    }

    @Test
    public void testGetCinema() {
        when(cinemaRepository.findById(1L)).thenReturn(Optional.of(cinema));

        Cinema result = cinemaService.getCinema(1L);

        assertEquals(cinema, result);
        verify(cinemaRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllCinemas() {
        when(cinemaRepository.findAll()).thenReturn(Collections.singletonList(cinema));

        Collection<Cinema> result = cinemaService.getAllCinemas();

        assertEquals(1, result.size());
        verify(cinemaRepository, times(1)).findAll();
    }

    @Test
    public void testFindCinemasByVille() {
        Ville ville = new Ville();
        when(cinemaRepository.findCinemasByVille(ville)).thenReturn(Collections.singletonList(cinema));

        Collection<Cinema> result = cinemaService.findCinemasByVille(ville);

        assertEquals(1, result.size());
        verify(cinemaRepository, times(1)).findCinemasByVille(ville);
    }
}
