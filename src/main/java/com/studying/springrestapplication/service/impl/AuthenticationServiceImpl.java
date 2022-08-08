package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.RefreshJwtRequest;
import com.studying.springrestapplication.security.jwt.JwtProvider;
import com.studying.springrestapplication.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse getJwtIfSuccessfulLogin(JwtRequest jwtRequest) {
        authentication(jwtRequest);

        String username = jwtRequest.getUsername();

        return getJwtResponse(username);
    }

    private void authentication(JwtRequest jwtRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());

            authenticationProvider.authenticate(authenticationToken);
        } catch (AuthenticationException authenticationException) {

            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    private JwtResponse getJwtResponse(String username) {
        String accessToken = jwtProvider.generateAccessToken(username);
        String refreshToken = jwtProvider.generateRefreshToken(username);

        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse getNewAccessTokenUsingRefreshToken(RefreshJwtRequest refreshJwtRequest) {
        String username = getUsernameIfRefreshTokenIsValid(refreshJwtRequest);

        String accessToken = jwtProvider.generateAccessToken(username);

        return new JwtResponse(accessToken, "");
    }

    @Override
    public JwtResponse getNewJwtUsingRefreshToken(RefreshJwtRequest refreshJwtRequest) {
        String username = getUsernameIfRefreshTokenIsValid(refreshJwtRequest);

        return getJwtResponse(username);
    }

    private String getUsernameIfRefreshTokenIsValid(RefreshJwtRequest refreshJwtRequest) {
        String refreshToken = refreshJwtRequest.getRefreshToken();

        if (jwtProvider.isRefreshTokenValid(refreshToken)) {
            Claims claims = jwtProvider.getRefreshTokenClaims(refreshToken);

            return claims.getSubject();
        }

        throw new JwtException("Invalid refresh token");
    }
}