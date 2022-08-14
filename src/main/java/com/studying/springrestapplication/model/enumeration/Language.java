package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language implements DefaultEnum<String> {
    ENGLISH("EN");

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
