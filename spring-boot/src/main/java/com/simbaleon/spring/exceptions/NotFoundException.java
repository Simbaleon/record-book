package com.simbaleon.spring.exceptions;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
    }

    public NotFoundException(Class<?> clazz, String fieldName, String value) {
        super(String.format("Entity %s with %s %s not found", clazz.getSimpleName(), fieldName, value));
    }

    public NotFoundException(Class<?> clazz, Long id) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id.toString()));
    }

    public NotFoundException(Class<?> clazz, Object... params) {
        super(String.format("Entity %s with params " + StringUtils.repeat("%s, ", params.length)
                , clazz.getSimpleName(), Arrays.toString(params)));
    }

}
