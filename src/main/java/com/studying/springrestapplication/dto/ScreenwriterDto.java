package com.studying.springrestapplication.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScreenwriterDto extends FilmCrewMemberDto {

    public ScreenwriterDto(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
