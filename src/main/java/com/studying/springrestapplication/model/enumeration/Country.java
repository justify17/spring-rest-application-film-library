package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Country {
    USA("USA"),
    NEW_ZEALAND("New Zealand");

    @Getter
    @JsonValue
    private final String name;
}
