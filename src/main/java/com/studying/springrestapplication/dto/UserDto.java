package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "The field 'username' must not be empty")
    @Pattern(regexp = "[0-9a-zA-Z]{4,}",
            message = "The field 'username' must contain at least 4 characters and contain only numbers or Latin letters")
    private String username;

    @NotBlank(message = "The field 'password' must not be empty")
    @Pattern(regexp = "[0-9a-zA-Z]{4,}",
            message = "The field 'password' must contain at least 4 characters and contain only numbers or Latin letters")
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
