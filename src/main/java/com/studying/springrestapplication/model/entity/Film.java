package com.studying.springrestapplication.model.entity;

import com.studying.springrestapplication.model.enumeration.Country;
import com.studying.springrestapplication.model.enumeration.Genre;
import com.studying.springrestapplication.model.enumeration.Language;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"title", "releaseDate", "runningTime", "language"})
@ToString(of = {"title", "releaseDate", "runningTime", "language"})
@NoArgsConstructor
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false, unique = true)
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "genre", length = 32, nullable = false)
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "screenwriter_id"))
    private Set<Screenwriter> screenwriters = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private Set<Producer> producers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @MapKeyColumn(name = "character_name", length = 64)
    private Map<String, Actor> characters = new HashMap<>();

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private int runningTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "films_countries",
            joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "country", length = 32, nullable = false)
    private Set<Country> countries = new HashSet<>();

    @Column(length = 2, nullable = false)
    private Language language;

    public Film(String title, LocalDate releaseDate, Integer runningTime, Language language) {
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

    public void addDirector(Director director) {
        directors.add(director);
    }

    public void removeDirector(Director director) {
        directors.remove(director);
    }

    public void addScreenwriter(Screenwriter screenwriter) {
        screenwriters.add(screenwriter);
    }

    public void removeScreenwriter(Screenwriter screenwriter) {
        screenwriters.remove(screenwriter);
    }

    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    public void removeProducer(Producer producer) {
        producers.remove(producer);
    }

    public void addCharacter(String characterName, Actor actor) {
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
