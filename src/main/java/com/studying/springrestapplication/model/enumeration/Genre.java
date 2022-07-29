package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Genre {
    KIDS("Kids"),
    FANTASY("Fantasy"),
    ADVENTURE("Adventure"),
    ACTION("Action");

    @Getter
    @JsonValue
    private final String name;
}
