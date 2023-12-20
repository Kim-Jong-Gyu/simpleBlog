package com.example.simpleblogproject.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionResponseCode {

    // 패스워드에 닉네임이 들어가 있다.
    PASSWORD_CONTAIN_NICKNAME(HttpStatus.BAD_REQUEST, "비밀번호에 닉네임이 포함되어 있습니다."),
    //비밀번호랑 비밀번호 확인이랑 일치하지 않다.
    PASSWORD_MACH_CHECKPASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 비밀번호 확인가 일치하지 않습니다."),
    //중복된 닉네임입니다.
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    //이메일 인증 코드가 일치하지 않습니다.
    EMAIL_CODE_NOT_MATCH(HttpStatus.BAD_REQUEST, "이메일 인증번호가 일치하지 않습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
