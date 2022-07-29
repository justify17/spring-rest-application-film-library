package com.studying.springrestapplication.service;

import com.studying.springrestapplication.dto.FilmDto;

import java.util.List;

public interface FilmService {
    List<FilmDto> getAllFilms();

    FilmDto getFilmById(Long id);

    void saveFilm(FilmDto filmDto);

    void updateFilm(FilmDto filmDto);

    void deleteFilmById(Long id);
}
