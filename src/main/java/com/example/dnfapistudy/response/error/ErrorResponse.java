package com.example.dnfapistudy.response.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final List<ErrorDTO> validation;


    @Builder
    public ErrorResponse(String code, String message, List<ErrorDTO> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(List<FieldError>  errors) {
        errors.stream().map(ErrorDTO::new).forEach(this.validation::add);
    }

    public void addValidation(FieldError  errors) {
        this.validation.add(new ErrorDTO(errors));
    }

}
