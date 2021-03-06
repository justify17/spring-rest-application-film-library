package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public List<FilmDto> getFilms() {

        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public FilmDto getFilm(@PathVariable Long id) {

        return filmService.getFilmById(id);
    }

    @PostMapping
    public void createFilm(@RequestBody FilmDto filmDto) {
        filmService.saveFilm(filmDto);
    }

    @PutMapping
    public void updateFilm(@RequestBody FilmDto filmDto) {
        filmService.updateFilm(filmDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmService.deleteFilmById(id);
    }
}
