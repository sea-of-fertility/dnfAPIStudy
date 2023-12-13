package com.example.dnfapistudy.controller;


import com.example.dnfapistudy.exception.CustomExceptionHandler;
import com.example.dnfapistudy.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();
        errorResponse.addValidation(e.getFieldErrors());
        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomExceptionHandler e) {
        int statusCode = e.statusCode();
        ErrorResponse body = ErrorResponse.builder()
                    .code(String.valueOf(statusCode))
                    .message(e.getMessage())
                    .build();

        body.addValidation(e.getValidation());
        return  ResponseEntity.status(statusCode)
                .body(body);
    }

}
