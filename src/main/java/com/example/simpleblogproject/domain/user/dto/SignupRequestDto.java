package com.example.simpleblogproject.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
    private String email;
}
