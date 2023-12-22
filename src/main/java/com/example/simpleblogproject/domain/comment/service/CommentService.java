package com.example.simpleblogproject.domain.comment.service;

import com.example.simpleblogproject.domain.comment.dto.AddCommentRequestDto;
import com.example.simpleblogproject.domain.comment.entity.Comment;
import com.example.simpleblogproject.domain.comment.repository.CommentRepository;
import com.example.simpleblogproject.domain.post.entity.Post;
import com.example.simpleblogproject.domain.post.repository.PostRepository;
import com.example.simpleblogproject.domain.user.entity.User;
import com.example.simpleblogproject.domain.user.repository.UserRepository;
import com.example.simpleblogproject.global.common.CommonResponse;
import com.example.simpleblogproject.global.common.CommonResponseCode;
import com.example.simpleblogproject.global.exception.CustomException;
import com.example.simpleblogproject.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public CommonResponse addComment(Long userId, AddCommentRequestDto requestDto, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_USER)
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_POST)
        );
        Comment comment = Comment.builder()
                .comment(requestDto.getComment())
                .user(user)
                .post(post)
                .build();
        user.addComment(comment);
        post.addComment(comment);
        commentRepository.save(comment);
        return new CommonResponse(CommonResponseCode.ADD_COMMENT_SUCCESS);
    }


}
