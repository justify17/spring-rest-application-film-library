package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Genre implements DefaultEnum<String> {
    FANTASY("Fantasy"),
    ADVENTURE("Adventure"),
    ACTION("Action");

    @JsonValue
    private final String name;

    @Override
    public String getValue() {

        return name;
    }
}
