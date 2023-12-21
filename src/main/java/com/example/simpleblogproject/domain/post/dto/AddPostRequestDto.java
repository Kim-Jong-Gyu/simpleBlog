package com.example.simpleblogproject.domain.post.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class AddPostRequestDto {
    @Size(max = 500)
    private String title;
    @Size(max = 5000)
    private String content;
    private MultipartFile picture;
}
