package com.studying.springrestapplication.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public enum Country implements DefaultEnum<String> {
    USA("USA"),
    NEW_ZEALAND("New Zealand");

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
