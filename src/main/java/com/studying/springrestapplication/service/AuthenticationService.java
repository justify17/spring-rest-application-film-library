package com.studying.springrestapplication.service;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;

public interface AuthenticationService {
    JwtResponse getJwtResponseIfSuccessfulLogin(JwtRequest jwtRequest);
}
