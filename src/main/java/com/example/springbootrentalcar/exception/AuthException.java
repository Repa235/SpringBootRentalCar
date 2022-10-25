package com.example.springbootrentalcar.exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Bad credentials");
    }
}
