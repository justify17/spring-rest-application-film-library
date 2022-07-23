package com.studying.springrestapplication.model.entity.enumeration.converter;

import com.studying.springrestapplication.model.entity.enumeration.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre genre) {

        return genre.getName();
    }

    @Override
    public Genre convertToEntityAttribute(String name) {
        Genre[] genres = Genre.values();

        for (Genre genre : genres) {
            if (genre.getName().equals(name)) {

                return genre;
            }
        }

        throw new IllegalArgumentException("Unknown genre name " + name);
    }
}
