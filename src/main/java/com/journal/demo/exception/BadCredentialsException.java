package com.journal.demo.exception;

import org.springframework.http.HttpStatus;

public class BadCredentialsException extends CustomException {

    public BadCredentialsException() {
        super("Bad Credentials", HttpStatus.UNAUTHORIZED);
    }
}
