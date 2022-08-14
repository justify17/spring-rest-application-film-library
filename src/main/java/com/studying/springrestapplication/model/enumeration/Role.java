package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role implements DefaultEnum<String> {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    @JsonValue
    private final String name;

    @Override
    public String getValue() {

        return name;
    }

    @Override
    public String toString() {

        return name;
    }
}
