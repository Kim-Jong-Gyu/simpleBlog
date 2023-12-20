package com.example.simpleblogproject.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResponseCode {

    // 회원가입에 성공했습니다.
    USER_CREATE(HttpStatus.CREATED, "회원가입에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}