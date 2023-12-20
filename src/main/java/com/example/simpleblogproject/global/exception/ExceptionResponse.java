package com.example.simpleblogproject.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExceptionResponse {
    private Integer status;
    private String msg;

    public ExceptionResponse(ExceptionResponseCode code) {
        this.status = code.getHttpStatus().value();
        this.msg = code.getMessage();
    }
}
