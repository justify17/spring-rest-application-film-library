package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.RefreshJwtRequest;
import com.studying.springrestapplication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationService.getJwtIfSuccessfulLogin(jwtRequest);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/newAccessToken")
    public ResponseEntity<?> getNewAccessToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        JwtResponse jwtResponse = authenticationService.getNewAccessTokenUsingRefreshToken(refreshJwtRequest);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/newJwt")
    public ResponseEntity<?> getNewJwt(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        JwtResponse jwtResponse = authenticationService.getNewJwtUsingRefreshToken(refreshJwtRequest);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
