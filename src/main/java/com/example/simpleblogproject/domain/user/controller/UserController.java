package com.example.simpleblogproject.domain.user.controller;


import com.example.simpleblogproject.domain.user.dto.SignupRequestDto;
import com.example.simpleblogproject.domain.user.service.UserService;
import com.example.simpleblogproject.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody SignupRequestDto requestDto){
        CommonResponse commonResponse = userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping("/validateDuplicateNickname/{nickname}")
    public ResponseEntity<CommonResponse> validateDuplicateNickname(@PathVariable String nickname){
        CommonResponse commonResponse = userService.validateDuplicateUser(nickname);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}
