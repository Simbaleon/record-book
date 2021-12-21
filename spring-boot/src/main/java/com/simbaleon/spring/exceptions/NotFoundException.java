package com.simbaleon.spring.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
    }

    public NotFoundException(Class<?> clazz, String name) {
        super(String.format("Entity %s with name %s not found", clazz.getSimpleName(), name));
    }

    public NotFoundException(Class<?> clazz, Long id) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id.toString()));
    }

}
