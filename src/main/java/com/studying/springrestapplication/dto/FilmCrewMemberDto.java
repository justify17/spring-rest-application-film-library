package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmCrewMemberDto {
    private Long id;
    private String firstName;
    private String lastName;

    public FilmCrewMemberDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
