package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RefreshJwtRequest {

    @NotBlank(message = "The field 'refreshToken' must not be empty")
    private String refreshToken;

    public RefreshJwtRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
