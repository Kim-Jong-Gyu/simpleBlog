package com.example.simpleblogproject.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResponseCode {

    // 회원가입에 성공했습니다.
    USER_CREATE(HttpStatus.CREATED, "회원가입에 성공했습니다."),
    USER_NICKNAME_OK(HttpStatus.OK, "사용 가능한 닉네임입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}