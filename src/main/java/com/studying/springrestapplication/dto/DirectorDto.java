package com.studying.springrestapplication.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DirectorDto extends FilmCrewMemberDto {

    public DirectorDto(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
