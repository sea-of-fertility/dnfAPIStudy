package com.example.dnfapistudy.response.error;


import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ErrorDTO {
    private final String field;
    private final String errorMessage;


    @Builder
    public ErrorDTO(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public ErrorDTO(FieldError fieldError) {
        this.field = fieldError.getField();
        this.errorMessage = fieldError.getDefaultMessage();
    }
}
