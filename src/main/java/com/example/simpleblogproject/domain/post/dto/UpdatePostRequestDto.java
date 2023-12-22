package com.example.simpleblogproject.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UpdatePostRequestDto {
    private String title;
    private String content;
    private MultipartFile picture;
}
