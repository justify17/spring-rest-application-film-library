package com.studying.springrestapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshJwtRequest {
    private String refreshToken;

    public RefreshJwtRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
