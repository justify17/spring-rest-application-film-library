package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.Language;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageConverter extends DefaultEnumConverter<Language, String> {

    public LanguageConverter() {
        super(Language.class);
    }
}
