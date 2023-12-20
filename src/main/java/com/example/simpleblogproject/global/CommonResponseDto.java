package com.example.simpleblogproject.global;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponseDto {
    private Integer status;
    private String msg;

    public CommonResponseDto(CommonResponseCode code) {
        this.status = code.getHttpStatus().value();
        this.msg = code.getMessage();
    }

}
