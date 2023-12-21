package com.example.simpleblogproject.domain.post.controller;

import com.example.simpleblogproject.domain.post.dto.AddPostRequestDto;
import com.example.simpleblogproject.domain.post.service.PostService;
import com.example.simpleblogproject.domain.security.userDetails.UserDetailsImpl;
import com.example.simpleblogproject.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CommonResponse> addPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @Valid @ModelAttribute AddPostRequestDto postRequestDto) throws UnsupportedEncodingException {
        CommonResponse commonResponse = postService.addPost(userDetails.getUser().getId(), postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

}
