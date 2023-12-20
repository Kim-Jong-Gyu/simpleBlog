package com.example.simpleblogproject.global.exception;

public class CustomException extends RuntimeException{
    private final ExceptionResponseCode exceptionCode;

    public CustomException(ExceptionResponseCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionResponseCode getExceptionCode() {
        return this.exceptionCode;
    }
}