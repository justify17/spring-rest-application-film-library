package com.studying.springrestapplication.model.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Language {
    ENGLISH("EN");

    @Getter
    private final String name;
}
