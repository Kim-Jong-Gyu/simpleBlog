package com.example.simpleblogproject.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponse {
    private Integer status;
    private String msg;

    public CommonResponse(CommonResponseCode code) {
        this.status = code.getHttpStatus().value();
        this.msg = code.getMessage();
    }

}
