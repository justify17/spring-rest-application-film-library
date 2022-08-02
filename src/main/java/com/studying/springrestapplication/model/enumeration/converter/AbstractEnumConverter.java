package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.AbstractEnum;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;

@RequiredArgsConstructor
public abstract class AbstractEnumConverter<E extends AbstractEnum<V>, V> implements AttributeConverter<E, V> {
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

        throw new IllegalArgumentException(String.format("Unknown %s name: %s", enumerationClass.getSimpleName(), value));
    }
}
