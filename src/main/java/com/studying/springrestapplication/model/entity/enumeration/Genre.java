package com.studying.springrestapplication.model.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Genre {
    KIDS("Kids"),
    FANTASY("Fantasy"),
    ADVENTURE("Adventure"),
    ACTION("Action");

    @Getter
    private final String name;
}
