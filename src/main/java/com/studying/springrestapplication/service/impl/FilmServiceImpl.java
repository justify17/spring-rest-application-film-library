package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.mapper.FilmMapper;
import com.studying.springrestapplication.model.entity.*;
import com.studying.springrestapplication.model.repository.FilmRepository;
import com.studying.springrestapplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final ActorService actorService;
    private final DirectorService directorService;
    private final ProducerService producerService;
    private final ScreenwriterService screenwriterService;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @Override
    public List<FilmDto> getAllFilms() {
        List<Film> films = filmRepository.findAll();

        return films.stream()
                .map(filmMapper::filmToFilmDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto getFilmById(Long id) {
        Film film = filmRepository.findById(id).orElse(null);

        return film != null ? filmMapper.filmToFilmDto(film) : null;
    }

    @Override
    public void saveFilm(FilmDto filmDto) {
        Film film = filmMapper.filmDtoToFilm(filmDto);

        checkIfFilmCrewMembersExist(film);

        filmRepository.save(film);
    }

    private void checkIfFilmCrewMembersExist(Film film) {
        Collection<Actor> actors = film.getCharacters().values();
        actorService.checkIfActorsExist(actors);

        Set<Director> directors = film.getDirectors();
        directorService.checkIfDirectorsExist(directors);

        Set<Producer> producers = film.getProducers();
        producerService.checkIfProducersExist(producers);

        Set<Screenwriter> screenwriters = film.getScreenwriters();
        screenwriterService.checkIfScreenwritersExist(screenwriters);
    }

    @Override
    public void updateFilm(FilmDto filmDto) {
        saveFilm(filmDto);
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }
}
