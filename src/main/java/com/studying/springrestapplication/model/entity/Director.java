package com.studying.springrestapplication.model.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "directors")
public class Director extends FilmCrewMember {

    public Director(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
