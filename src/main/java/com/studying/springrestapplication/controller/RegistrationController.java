package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.exception.UsernameIsTakenException;
import com.studying.springrestapplication.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Void> registration(@Valid @RequestBody UserDto userDto) {
        if (registrationService.isUserExisting(userDto)) {
            throw new UsernameIsTakenException(userDto.getUsername());
        }

        registrationService.registerUser(userDto);

        return ResponseEntity.ok().build();
    }
}
