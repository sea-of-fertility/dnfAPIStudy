package com.example.dnfapistudy.exception;

public class StorageException extends CustomExceptionHandler {

    public StorageException(String message) {
        super(message);
    }

    @Override
    public int statusCode() {
        return 500;
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
