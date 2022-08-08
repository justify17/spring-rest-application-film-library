package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        registrationService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
