package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.RefreshJwtRequest;
import com.studying.springrestapplication.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AuthenticationServiceImplTest {
    public static final String TEST_ACCESS_TOKEN = "Access Token";
    public static final String TEST_REFRESH_TOKEN = "Refresh Token";

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private JwtProvider jwtProvider;

    @Test
    void getJwtIfSuccessfulLogin_GivenRequest_AndSuccessfulAuthenticate_ReturnResponse() {
        JwtRequest jwtRequest = new JwtRequest("user", "password");

        when(jwtProvider.generateAccessToken(jwtRequest.getUsername())).thenReturn(TEST_ACCESS_TOKEN);
        when(jwtProvider.generateRefreshToken(jwtRequest.getUsername())).thenReturn(TEST_REFRESH_TOKEN);

        JwtResponse expected = new JwtResponse(TEST_ACCESS_TOKEN, TEST_REFRESH_TOKEN);
        JwtResponse actual = authenticationService.getJwtIfSuccessfulLogin(jwtRequest);

        assertEquals(expected, actual);
    }

    @Test
    void getJwtIfSuccessfulLogin_GivenRequest_AndUnsuccessfulAuthenticate_ThrowException() {
        JwtRequest jwtRequest = new JwtRequest("Not existing user", "password");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());

        when(authenticationProvider.authenticate(token)).thenThrow(new BadCredentialsException(jwtRequest.getUsername()));

        assertThrows(BadCredentialsException.class, () -> authenticationService.getJwtIfSuccessfulLogin(jwtRequest));
    }

    @Test
    void getNewAccessTokenUsingRefreshToken_GivenValidRefreshToken_ReturnResponse() {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest(TEST_REFRESH_TOKEN);

        when(jwtProvider.isRefreshTokenValid(TEST_REFRESH_TOKEN)).thenReturn(true);

        Claims claims = new DefaultClaims();
        claims.setSubject("user");

        when(jwtProvider.getRefreshTokenClaims(TEST_REFRESH_TOKEN)).thenReturn(claims);
        when(jwtProvider.generateAccessToken("user")).thenReturn(TEST_ACCESS_TOKEN);

        JwtResponse expected = new JwtResponse(TEST_ACCESS_TOKEN, "");
        JwtResponse actual = authenticationService.getNewAccessTokenUsingRefreshToken(refreshJwtRequest);

        assertEquals(expected, actual);
    }

    @Test
    void getNewAccessTokenUsingRefreshToken_GivenInvalidRefreshToken_ThrowException() {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest(TEST_REFRESH_TOKEN);

        when(jwtProvider.isRefreshTokenValid(TEST_REFRESH_TOKEN)).thenReturn(false);

        assertThrows(JwtException.class, () -> authenticationService.getNewAccessTokenUsingRefreshToken(refreshJwtRequest));
    }

    @Test
    void getNewJwtUsingRefreshToken_GivenValidRefreshToken_ReturnResponse() {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest(TEST_REFRESH_TOKEN);

        when(jwtProvider.isRefreshTokenValid(TEST_REFRESH_TOKEN)).thenReturn(true);

        Claims claims = new DefaultClaims();
        claims.setSubject("user");

        when(jwtProvider.getRefreshTokenClaims(TEST_REFRESH_TOKEN)).thenReturn(claims);
        when(jwtProvider.generateAccessToken("user")).thenReturn(TEST_ACCESS_TOKEN);
        when(jwtProvider.generateRefreshToken("user")).thenReturn(TEST_REFRESH_TOKEN);

        JwtResponse expected = new JwtResponse(TEST_ACCESS_TOKEN, TEST_REFRESH_TOKEN);
        JwtResponse actual = authenticationService.getNewJwtUsingRefreshToken(refreshJwtRequest);

        assertEquals(expected, actual);
    }

    @Test
    void getNewJwtUsingRefreshToken_GivenInvalidRefreshToken_ThrowException() {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest(TEST_REFRESH_TOKEN);

        when(jwtProvider.isRefreshTokenValid(TEST_REFRESH_TOKEN)).thenReturn(false);

        assertThrows(JwtException.class, () -> authenticationService.getNewJwtUsingRefreshToken(refreshJwtRequest));
    }
}