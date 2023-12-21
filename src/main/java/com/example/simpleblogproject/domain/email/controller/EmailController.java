package com.example.simpleblogproject.domain.email.controller;

import com.example.simpleblogproject.domain.email.dto.EmailRequestDto;
import com.example.simpleblogproject.domain.email.service.EmailService;
import com.example.simpleblogproject.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<CommonResponse> send(@Valid @RequestBody EmailRequestDto requestDto){
        CommonResponse commonResponse = emailService.send(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
