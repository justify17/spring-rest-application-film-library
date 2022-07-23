package com.studying.springrestapplication.model.entity.enumeration.converter;

import com.studying.springrestapplication.model.entity.enumeration.Country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryConverter implements AttributeConverter<Country, String> {
    @Override
    public String convertToDatabaseColumn(Country country) {

        return country.getName();
    }

    @Override
    public Country convertToEntityAttribute(String name) {
        Country[] countries = Country.values();

        for (Country country : countries) {
            if (country.getName().equals(name)) {

                return country;
            }
        }

        throw new IllegalArgumentException("Unknown country name " + name);
    }
}
