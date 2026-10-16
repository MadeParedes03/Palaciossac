package com.project.Palaciossac.customer.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User not found: " + id);
    }
}
