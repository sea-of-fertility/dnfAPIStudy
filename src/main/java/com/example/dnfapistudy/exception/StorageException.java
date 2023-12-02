package com.example.dnfapistudy.exception;

public class StorageException extends RuntimeException {

    private final String MESSAGE = "저장되지 않았습니다.";

    public StorageException(String MESSAGE) {
        super();
    }

    public StorageException(String MESSAGE, Throwable cause) {
        super(MESSAGE, cause);
    }
}
