package com.example.dnfapistudy.exception;

public class StorageFileNotFoundException extends RuntimeException {

    private final String MESSAGE = "이미지가 없습니다.";
    public StorageFileNotFoundException(String MESSAGE) {
    }

    public StorageFileNotFoundException(String MESSAGE, Throwable cause) {
        super(MESSAGE, cause);
    }
}
