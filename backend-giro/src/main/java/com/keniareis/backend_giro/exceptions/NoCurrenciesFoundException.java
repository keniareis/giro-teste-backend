package com.keniareis.backend_giro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class NoCurrenciesFoundException extends RuntimeException{
    public NoCurrenciesFoundException(String message) {
        super(message);
    }
}
