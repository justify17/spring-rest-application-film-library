package com.studying.springrestapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.studying.springrestapplication.model.enumeration.Country;
import com.studying.springrestapplication.model.enumeration.Genre;
import com.studying.springrestapplication.model.enumeration.Language;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Data
public class FilmDto {
    private String title;
    private Set<Genre> genres;
    private Set<DirectorDto> directors;
    private Set<ScreenwriterDto> screenwriters;
    private Set<ProducerDto> producers;
    private Map<String, ActorDto> characters;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate releaseDate;

    private int runningTime;
    private Set<Country> countries;
    private Language language;
}
