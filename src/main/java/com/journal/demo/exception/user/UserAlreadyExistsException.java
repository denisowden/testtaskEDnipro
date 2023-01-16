package com.journal.demo.exception.user;


import com.journal.demo.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CustomException {

    public UserAlreadyExistsException(String email) {
        super("User already exists with email " + email, HttpStatus.CONFLICT);
    }
}
