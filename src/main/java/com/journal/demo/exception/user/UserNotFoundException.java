package com.journal.demo.exception.user;


import com.journal.demo.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(String email) {
        super("User not found with email: " + email, HttpStatus.NOT_FOUND);
    }
}
