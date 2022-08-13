package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class FilmCrewMemberDto {
    private Long id;

    @NotBlank(message = "The field 'firstName' must not be empty")
    private String firstName;

    @NotBlank(message = "The field 'lastName' must not be empty")
    private String lastName;

    public FilmCrewMemberDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
