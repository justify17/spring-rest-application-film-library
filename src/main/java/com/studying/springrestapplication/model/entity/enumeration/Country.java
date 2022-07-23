package com.studying.springrestapplication.model.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Country {
    USA("USA"),
    NEW_ZEALAND("New Zealand");

    @Getter
    private final String name;
}
