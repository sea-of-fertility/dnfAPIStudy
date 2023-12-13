package com.example.dnfapistudy.exception;

public class CharaNotFoundException extends CustomExceptionHandler{

    private final static String MESSAGE = "캐릭터가 없습니다.";

    public CharaNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 404;
    }

    public CharaNotFoundException(String MESSAGE, Throwable cause) {
        super(MESSAGE, cause);
    }

}
