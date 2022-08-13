package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class JwtRequest {

    @NotBlank(message = "The field 'username' must not be empty")
    private String username;

    @NotBlank(message = "The field 'password' must not be empty")
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
