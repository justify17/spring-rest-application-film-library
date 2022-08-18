package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.exception.EntityByIdNotFoundException;
import com.studying.springrestapplication.mapper.FilmMapper;
import com.studying.springrestapplication.model.entity.Film;
import com.studying.springrestapplication.model.enumeration.Language;
import com.studying.springrestapplication.model.repository.FilmRepository;
import com.studying.springrestapplication.service.ActorService;
import com.studying.springrestapplication.service.DirectorService;
import com.studying.springrestapplication.service.ProducerService;
import com.studying.springrestapplication.service.ScreenwriterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private ActorService actorService;

    @Mock
    private DirectorService directorService;

    @Mock
    private ProducerService producerService;

    @Mock
    private ScreenwriterService screenwriterService;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    private Film film;
    private FilmDto filmDto;

    @BeforeEach
    void init() {
        film = new Film("New Film", LocalDate.now(), 130, Language.ENGLISH);
        filmDto = new FilmDto("New Film", LocalDate.now(), 130, Language.ENGLISH);
    }

    @Test
    void getAllFilms() {
        List<Film> films = List.of(film);

        when(filmRepository.findAll()).thenReturn(films);
        when(filmMapper.filmToFilmDto(film)).thenReturn(filmDto);

        List<FilmDto> expected = List.of(filmDto);
        List<FilmDto> actual = filmService.getAllFilms();

        assertEquals(expected, actual);
    }

    @Test
    void getFilmById_AndFilmExists_ReturnFilm() {
        Long id = 1L;

        when(filmRepository.findById(id)).thenReturn(Optional.of(film));
        when(filmMapper.filmToFilmDto(film)).thenReturn(filmDto);

        assertEquals(filmDto, filmService.getFilmById(id));
    }

    @Test
    void getFilmById_AndFilmDoesNotExist_ThrowException() {
        Long id = 1L;

        when(filmRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityByIdNotFoundException.class, () -> filmService.getFilmById(id));
    }

    @Test
    void saveFilm() {
        when(filmMapper.filmDtoToFilm(filmDto)).thenReturn(film);

        filmService.saveFilm(filmDto);

        verify(actorService, times(1)).checkIfFilmCrewMembersExist(film.getCharacters().values());
        verify(directorService, times(1)).checkIfFilmCrewMembersExist(film.getDirectors());
        verify(producerService, times(1)).checkIfFilmCrewMembersExist(film.getProducers());
        verify(screenwriterService, times(1)).checkIfFilmCrewMembersExist(film.getScreenwriters());
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void updateFilm() {
        when(filmMapper.filmDtoToFilm(filmDto)).thenReturn(film);

        filmService.updateFilm(filmDto);

        verify(actorService, times(1)).checkIfFilmCrewMembersExist(film.getCharacters().values());
        verify(directorService, times(1)).checkIfFilmCrewMembersExist(film.getDirectors());
        verify(producerService, times(1)).checkIfFilmCrewMembersExist(film.getProducers());
        verify(screenwriterService, times(1)).checkIfFilmCrewMembersExist(film.getScreenwriters());
        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void deleteFilmById() {
        Long id = 10L;

        filmService.deleteFilmById(id);

        verify(filmRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFilmById_AndFilmDoesNotExist_ThrowException() {
        Long id = 0L;

        doThrow(new EmptyResultDataAccessException(1)).when(filmRepository).deleteById(id);

        assertThrows(EntityByIdNotFoundException.class, () -> filmService.getFilmById(id));
    }
}