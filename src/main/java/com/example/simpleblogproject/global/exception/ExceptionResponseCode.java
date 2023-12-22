package com.example.simpleblogproject.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionResponseCode {

    // 해당 유저가 없습니다.
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저가 없습니다."),
    // 패스워드에 닉네임이 들어가 있다.
    PASSWORD_CONTAIN_NICKNAME(HttpStatus.BAD_REQUEST, "비밀번호에 닉네임이 포함되어 있습니다."),
    //비밀번호랑 비밀번호 확인이랑 일치하지 않다.
    PASSWORD_MACH_CHECKPASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 비밀번호 확인가 일치하지 않습니다."),
    //중복된 닉네임입니다.
    NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    //이메일 인증 코드가 일치하지 않습니다.
    EMAIL_CODE_NOT_MATCH(HttpStatus.BAD_REQUEST, "이메일 인증번호가 일치하지 않습니다."),
    // 해당 포스트가 없습니다.
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다."),
    //백오피스 기능 활성화 X
    NOT_OPERATE_BACKOFFICE(HttpStatus.BAD_REQUEST, "백 오피스 기능을 아직 구현하지 않았습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
