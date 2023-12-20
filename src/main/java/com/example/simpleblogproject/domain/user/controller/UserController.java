package com.example.simpleblogproject.domain.user.controller;


import com.example.simpleblogproject.domain.user.dto.SignupRequestDto;
import com.example.simpleblogproject.domain.user.service.UserService;
import com.example.simpleblogproject.global.CommonResponseCode;
import com.example.simpleblogproject.global.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto){
        CommonResponseDto commonResponseDto = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

}
