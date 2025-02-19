package com.example.finalEclips.eclips.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistedUserException extends RuntimeException {
    public AlreadyExistedUserException(String message) {
        super(message);
    }
}
