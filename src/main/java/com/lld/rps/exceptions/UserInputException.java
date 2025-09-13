package com.lld.rps.exceptions;

/** Exception thrown when a user inputs invalid characters or exceeds attempt limit. */
public class UserInputException extends RuntimeException {
    public UserInputException(String message) {
        super(message);
    }
}
