package com.studying.springrestapplication.service;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.RefreshJwtRequest;

public interface AuthenticationService {
    JwtResponse getJwtIfSuccessfulLogin(JwtRequest jwtRequest);

    JwtResponse getNewAccessTokenUsingRefreshToken(RefreshJwtRequest refreshJwtRequest);

    JwtResponse getNewJwtUsingRefreshToken(RefreshJwtRequest refreshJwtRequest);
}
