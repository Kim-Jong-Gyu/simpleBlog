package com.example.simpleblogproject.domain.comment.dto;


import com.example.simpleblogproject.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetTotalCommentResponseDto {
    private String nickname;
    private String comment;

    public GetTotalCommentResponseDto(Comment comment){
        this.nickname = comment.getUser().getNickname();
        this.comment = comment.getComment();
    }
}
