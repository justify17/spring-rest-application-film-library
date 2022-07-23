package com.studying.springrestapplication.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"firstName", "lastName"})
@ToString(of = {"firstName", "lastName"})
@NoArgsConstructor
@MappedSuperclass
public class FilmCrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 32, nullable = false)
    private String lastName;

    public FilmCrewMember(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
