package com.example.dnfapistudy.exception;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Getter
public abstract class CustomExceptionHandler extends RuntimeException{

    private final List<FieldError> validation = new ArrayList<>();

    public CustomExceptionHandler(String message) {
        super(message);
    }

    public abstract int statusCode();

    public CustomExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
