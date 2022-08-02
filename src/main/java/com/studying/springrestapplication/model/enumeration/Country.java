package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Country implements AbstractEnum<String> {
    USA("USA"),
    NEW_ZEALAND("New Zealand");

    @JsonValue
    private final String name;

    @Override
    public String getValue() {

        return name;
    }
}
