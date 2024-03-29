package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.Country;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryConverter extends DefaultEnumConverter<Country, String> {

    public CountryConverter() {
        super(Country.class);
    }
}
