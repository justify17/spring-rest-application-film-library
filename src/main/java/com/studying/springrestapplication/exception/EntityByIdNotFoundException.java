package com.studying.springrestapplication.exception;

public class EntityByIdNotFoundException extends RuntimeException {

    public EntityByIdNotFoundException(Class<?> entityClass, long id) {
        super(String.format("%s with id %d does not exist", entityClass.getSimpleName(), id));
    }
}
