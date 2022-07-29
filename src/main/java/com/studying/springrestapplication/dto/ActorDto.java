package com.studying.springrestapplication.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ActorDto extends FilmCrewMemberDto {

    public ActorDto(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
