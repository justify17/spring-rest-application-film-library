package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Language {
    ENGLISH("EN");

    @Getter
    @JsonValue
    private final String name;
}
