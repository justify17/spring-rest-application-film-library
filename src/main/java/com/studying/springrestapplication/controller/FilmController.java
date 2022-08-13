package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDto>> getFilms() {
        List<FilmDto> films = filmService.getAllFilms();

        return films.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilm(@PathVariable Long id) {
        FilmDto film = filmService.getFilmById(id);

        return ResponseEntity.ok(film);
    }

    @PostMapping
    public ResponseEntity<Void> createFilm(@Valid @RequestBody FilmDto filmDto) {
        filmService.saveFilm(filmDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateFilm(@Valid @RequestBody FilmDto filmDto) {
        filmService.updateFilm(filmDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilmById(id);

        return ResponseEntity.ok().build();
    }
}
