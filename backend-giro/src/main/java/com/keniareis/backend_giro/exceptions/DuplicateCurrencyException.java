package com.keniareis.backend_giro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) 
public class DuplicateCurrencyException extends RuntimeException {
    public DuplicateCurrencyException(String message) {
        super(message);
    }
}
