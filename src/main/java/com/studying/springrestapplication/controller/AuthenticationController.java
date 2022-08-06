package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.service.AuthenticationService;
import com.studying.springrestapplication.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final SecurityService securityService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        securityService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authentication(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = authenticationService.getJwtResponseIfSuccessfulLogin(jwtRequest);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
