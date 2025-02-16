package com.keniareis.backend_giro.exceptions;

public class DuplicateCurrencyException extends RuntimeException {
    public DuplicateCurrencyException(String message) {
        super(message);
    }
}
