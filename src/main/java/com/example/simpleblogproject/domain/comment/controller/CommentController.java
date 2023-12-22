package com.example.simpleblogproject.domain.comment.controller;


import com.example.simpleblogproject.domain.comment.dto.AddCommentRequestDto;
import com.example.simpleblogproject.domain.comment.service.CommentService;
import com.example.simpleblogproject.domain.security.userDetails.UserDetailsImpl;
import com.example.simpleblogproject.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{postId}")
    public ResponseEntity<CommonResponse> addComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody AddCommentRequestDto requestDto,
                                                     @PathVariable Long postId)
    {
        CommonResponse commonResponse = commentService.addComment(userDetails.getUser().getId(), requestDto, postId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PatchMapping("/{postId}/like")
    public ResponseEntity<CommonResponse> addLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @PathVariable Long postId)
    {
        CommonResponse commonResponse = commentService.addLike(userDetails.getUser().getId(), postId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}
