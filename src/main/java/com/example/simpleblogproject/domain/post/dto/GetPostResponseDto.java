package com.example.simpleblogproject.domain.post.dto;


import com.example.simpleblogproject.domain.comment.dto.GetCommentResponseDto;
import com.example.simpleblogproject.domain.comment.entity.Comment;
import com.example.simpleblogproject.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetPostResponseDto {
    private String nickname;
    private LocalDateTime createdAt;
    private String content;
    private List<GetCommentResponseDto> commentList;

    @Builder
    public GetPostResponseDto(String nickname, LocalDateTime createdAt, String content, List<GetCommentResponseDto> commentList){
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.content = content;
        this.commentList = commentList;
    }
}
