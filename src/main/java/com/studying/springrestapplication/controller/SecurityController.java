package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        securityService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
