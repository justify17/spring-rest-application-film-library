package com.studying.springrestapplication.model.enumeration.converter;

import com.studying.springrestapplication.model.enumeration.Role;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter extends DefaultEnumConverter<Role, String> {

    public RoleConverter() {
        super(Role.class);
    }
}
