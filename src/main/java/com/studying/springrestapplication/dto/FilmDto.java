package com.studying.springrestapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.studying.springrestapplication.model.enumeration.Country;
import com.studying.springrestapplication.model.enumeration.Genre;
import com.studying.springrestapplication.model.enumeration.Language;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
public class FilmDto {
    private Long id;
    private String title;
    private Set<Genre> genres = new HashSet<>();
    private Set<DirectorDto> directors = new HashSet<>();
    private Set<ScreenwriterDto> screenwriters = new HashSet<>();
    private Set<ProducerDto> producers = new HashSet<>();
    private Map<String, ActorDto> characters = new HashMap<>();

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDate;

    private int runningTime;
    private Set<Country> countries = new HashSet<>();
    private Language language;

    public FilmDto(String title, LocalDate releaseDate, int runningTime, Language language) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.language = language;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    public void addDirector(DirectorDto director) {
        directors.add(director);
    }

    public void removeDirector(DirectorDto director) {
        directors.remove(director);
    }

    public void addScreenwriter(ScreenwriterDto screenwriter) {
        screenwriters.add(screenwriter);
    }

    public void removeScreenwriter(ScreenwriterDto screenwriter) {
        screenwriters.remove(screenwriter);
    }

    public void addProducer(ProducerDto producer) {
        producers.add(producer);
    }

    public void removeProducer(ProducerDto producer) {
        producers.remove(producer);
    }

    public void addCharacter(String characterName, ActorDto actor) {
        characters.put(characterName, actor);
    }

    public void removeCharacter(String characterName) {
        characters.remove(characterName);
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
    }
}
