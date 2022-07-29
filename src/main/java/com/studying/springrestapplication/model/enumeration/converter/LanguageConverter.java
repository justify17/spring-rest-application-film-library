package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.Language;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageConverter implements AttributeConverter<Language, String> {
    @Override
    public String convertToDatabaseColumn(Language language) {

        return language.getName();
    }

    @Override
    public Language convertToEntityAttribute(String name) {
        Language[] languages = Language.values();

        for (Language language : languages) {
            if (language.getName().equalsIgnoreCase(name)) {

                return language;
            }
        }

        throw new IllegalArgumentException("Unknown language name " + name);
    }
}
