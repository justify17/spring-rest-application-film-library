package com.studying.springrestapplication.controller;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> registration(@RequestBody UserDto userDto) {
        registrationService.registerUser(userDto);

        return ResponseEntity.ok().build();
    }
}
