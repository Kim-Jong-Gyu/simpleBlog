package com.example.simpleblogproject.domain.post.dto;

import com.example.simpleblogproject.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetTotalPostsResponseDto {
    private String title;

    private String nickname;

    private LocalDateTime createdAt;

    public GetTotalPostsResponseDto(Post post){
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createdAt = post.getCreatedAt();
    }
}
