package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.Genre;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter extends AbstractEnumConverter<Genre, String> {

    public GenreConverter() {
        super(Genre.class);
    }
}
