package com.studying.springrestapplication.model.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "screenwriters")
public class Screenwriter extends FilmCrewMember {

    public Screenwriter(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
