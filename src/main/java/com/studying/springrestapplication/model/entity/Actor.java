package com.studying.springrestapplication.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor
@Entity
@Table(name = "actors", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
public class Actor extends FilmCrewMember {

    public Actor(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
