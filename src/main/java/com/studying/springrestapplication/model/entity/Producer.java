package com.studying.springrestapplication.model.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "producers", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
public class Producer extends FilmCrewMember {

    public Producer(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
