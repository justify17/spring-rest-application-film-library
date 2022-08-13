package com.studying.springrestapplication.exception;

public class UsernameIsTakenException extends RuntimeException {

    public UsernameIsTakenException(String username) {
        super(String.format("User with username '%s' already exists", username));
    }
}
