package com.studying.springrestapplication.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProducerDto extends FilmCrewMemberDto {

    public ProducerDto(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
