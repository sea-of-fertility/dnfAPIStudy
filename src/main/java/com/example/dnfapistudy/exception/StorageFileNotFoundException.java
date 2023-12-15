package com.example.dnfapistudy.exception;

public class StorageFileNotFoundException extends CustomExceptionHandler {

    private final String MESSAGE = "이미지가 없습니다.";
    public StorageFileNotFoundException(String MESSAGE) {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }

    public StorageFileNotFoundException(String MESSAGE, Throwable cause) {
        super(MESSAGE, cause);
    }
}
