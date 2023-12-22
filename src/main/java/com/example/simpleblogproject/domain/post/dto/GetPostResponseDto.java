package com.example.simpleblogproject.domain.post.dto;


import com.example.simpleblogproject.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetPostResponseDto {
    private String nickname;
    private LocalDateTime createdAt;
    private String content;

    @Builder
    public GetPostResponseDto(Post post){
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
        this.content = post.getContent();
    }
}
