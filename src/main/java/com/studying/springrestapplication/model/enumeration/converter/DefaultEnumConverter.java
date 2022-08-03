package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.DefaultEnum;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;

@RequiredArgsConstructor
public abstract class DefaultEnumConverter<E extends DefaultEnum<V>, V> implements AttributeConverter<E, V> {
    private final Class<E> enumerationClass;

    @Override
    public V convertToDatabaseColumn(E enumeration) {

        return enumeration.getValue();
    }

    @Override
    public E convertToEntityAttribute(V value) {
        E[] enumerations = enumerationClass.getEnumConstants();

        for (E enumeration : enumerations) {
            if (enumeration.getValue().equals(value)) {

                return enumeration;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown %s value: %s", enumerationClass.getSimpleName(), value));
    }
}
