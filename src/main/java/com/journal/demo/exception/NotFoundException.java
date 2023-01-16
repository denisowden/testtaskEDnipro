package com.journal.demo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public <T> NotFoundException(Class<T> clazz) {
        this(clazz.getSimpleName() + " not found");
    }
}
