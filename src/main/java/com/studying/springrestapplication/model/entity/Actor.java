package com.studying.springrestapplication.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "actors")
public class Actor extends FilmCrewMember {

    public Actor(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
