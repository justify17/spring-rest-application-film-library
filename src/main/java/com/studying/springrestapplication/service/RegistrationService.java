package com.studying.springrestapplication.service;

import com.studying.springrestapplication.dto.UserDto;

public interface RegistrationService {
    boolean isUserExisting(UserDto userDto);

    void registerUser(UserDto userDto);
}
