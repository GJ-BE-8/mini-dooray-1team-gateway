package com.nhnacademy.gateway.exception;

public class CreateFailureException extends RuntimeException {
    public CreateFailureException(String message) {
        super(message);
    }
}
