package com.example.dnfapistudy.exception;

public class CharaNotFoundException extends  RuntimeException{

    private final static String MESSAGE = "캐릭터가 없습니다.";

    public CharaNotFoundException() {
        super(MESSAGE);
    }

    public CharaNotFoundException(String MESSAGE, Throwable cause) {
        super(MESSAGE, cause);
    }

}
