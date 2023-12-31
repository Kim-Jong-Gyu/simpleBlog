package com.example.simpleblogproject.domain.comment.controller;


import com.example.simpleblogproject.domain.comment.dto.AddCommentRequestDto;
import com.example.simpleblogproject.domain.comment.dto.GetCommentResponseDto;
import com.example.simpleblogproject.domain.comment.dto.UpdateCommentRequestDto;
import com.example.simpleblogproject.domain.comment.service.CommentService;
import com.example.simpleblogproject.domain.security.userDetails.UserDetailsImpl;
import com.example.simpleblogproject.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommonResponse> addComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody AddCommentRequestDto requestDto,
                                                     @PathVariable Long postId) {
        CommonResponse commonResponse = commentService.addComment(userDetails.getUser().getId(), requestDto, postId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PatchMapping("/{postId}/like")
    public ResponseEntity<CommonResponse> addLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @PathVariable Long postId) {
        CommonResponse commonResponse = commentService.addLike(userDetails.getUser().getId(), postId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/total")
    public ResponseEntity<List<GetCommentResponseDto>> getTotalPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        List<GetCommentResponseDto> getCommentResponseDto = commentService.getTotalComments(page - 1, size, sortBy, isAsc);
        return ResponseEntity.status(HttpStatus.OK).body(getCommentResponseDto);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommonResponse> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody UpdateCommentRequestDto requestDto,
                                                        @PathVariable Long commentId
    ) {
        CommonResponse commonResponse = commentService.updateComment(userDetails.getUser().getId(), requestDto, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponse> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @PathVariable Long commentId) {
        CommonResponse commonResponse = commentService.deleteComment(userDetails.getUser().getId(), commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}
