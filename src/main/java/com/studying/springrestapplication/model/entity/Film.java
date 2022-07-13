package com.studying.springrestapplication.model.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Data
public class Film {
    private String title;
    private Set<Genre> genres;
    private Set<Director> directors;
    private Set<Screenwriter> screenwriters;
    private Set<Producer> producers;
    private Map<Actor, String> starring;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Set<Country> countries;
    private Language language;
}
