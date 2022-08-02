package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getFilms() {
        List<FilmDto> films = filmService.getAllFilms();

        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilm(@PathVariable Long id) {
        FilmDto film = filmService.getFilmById(id);

        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createFilm(@RequestBody FilmDto filmDto) {
        filmService.saveFilm(filmDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateFilm(@RequestBody FilmDto filmDto) {
        filmService.updateFilm(filmDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilmById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
