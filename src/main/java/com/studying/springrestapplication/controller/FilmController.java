package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.mapper.FilmMapper;
import com.studying.springrestapplication.model.entity.Film;
import com.studying.springrestapplication.model.repository.FilmRepository;
import com.studying.springrestapplication.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping(value = "/films")
    public List<Film> getFilms() {

        return null;
    }

    @GetMapping(value = "/films/{id}")
    public Film getFilm(@PathVariable Long id) {

        return null/*filmRepository.findById(id).orElse(null)*/;
    }

    @DeleteMapping(value = "/films/{id}")
    public void deleteFilm(@PathVariable Long id) {

    }

    @PostMapping(value = "/films")
    public void createFilm(@RequestBody FilmDto filmDto) {
        filmService.saveFilm(filmDto);
    }
}
