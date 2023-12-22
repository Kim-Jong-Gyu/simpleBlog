package com.example.simpleblogproject.domain.comment.service;

import com.example.simpleblogproject.domain.comment.dto.AddCommentRequestDto;
import com.example.simpleblogproject.domain.comment.dto.GetCommentResponseDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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
        commentRepository.save(comment);
//        for(Post temp : user.getPostList()){
//            if(Objects.equals(temp.getId(), postId)){
//                for(Comment comment1 : temp.getCommentList()){
//                    log.info(String.valueOf(comment1.getId()));
//                }
//            }
//        }
        return new CommonResponse(CommonResponseCode.ADD_COMMENT_SUCCESS);
    }


    @Transactional
    public CommonResponse addLike(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_USER)
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_POST)
        );
        post.addLike();
//        for(Post temp : user.getPostList()){
//            if(Objects.equals(temp.getId(), postId))
//                log.info(String.valueOf(temp.getCountLike()));
//        }
        return new CommonResponse(CommonResponseCode.ADD_LIKE_SUCCESS);
    }

    public List<GetCommentResponseDto> getTotalComments(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        return commentRepository.findAll(pageable).map(GetCommentResponseDto::new).getContent();
    }
}
