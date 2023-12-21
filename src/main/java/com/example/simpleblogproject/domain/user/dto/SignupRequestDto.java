package com.example.simpleblogproject.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

// BackOffice 기능 확장성을 위해 권환을 추가
@Getter
public class SignupRequestDto {

    @Size(min = 3)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String nickname;

    @Size(min = 4)
    private String password;

    @Size(min=4)
    private String checkPassword;

    @Email
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String email;

    @NotEmpty(message = "인증 번호를 입력해주세요")
    private String authNum;

    @ColumnDefault("false")
    private boolean admin;
}
