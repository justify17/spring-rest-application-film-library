package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.security.jwt.JwtProvider;
import com.studying.springrestapplication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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
    public JwtResponse getJwtResponseIfSuccessfulLogin(JwtRequest jwtRequest) {
        authentication(jwtRequest);

        return getJwtResponse(jwtRequest);
    }

    private void authentication(JwtRequest jwtRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());

            authenticationProvider.authenticate(authenticationToken);
        } catch (AuthenticationException authenticationException) {

            throw new BadCredentialsException("Incorrect login or password");
        }
    }

    private JwtResponse getJwtResponse(JwtRequest jwtRequest) {
        String accessToken = jwtProvider.generateAccessToken(jwtRequest.getUsername());
        String refreshToken = jwtProvider.generateRefreshToken(jwtRequest.getUsername());

        return new JwtResponse(accessToken, refreshToken);
    }
}
