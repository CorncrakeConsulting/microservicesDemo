package com.corncrakeconsulting.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;



    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
