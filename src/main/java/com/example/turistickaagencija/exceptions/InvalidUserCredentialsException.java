package com.example.turistickaagencija.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super(String.format("Invalid credentials. Please try again"));
    }
}
