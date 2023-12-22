package com.example.simpleblogproject.domain.post.controller;

import com.example.simpleblogproject.domain.post.dto.AddPostRequestDto;
import com.example.simpleblogproject.domain.post.dto.GetPostResponseDto;
import com.example.simpleblogproject.domain.post.dto.GetTotalPostsResponseDto;
import com.example.simpleblogproject.domain.post.dto.UpdatePostRequestDto;
import com.example.simpleblogproject.domain.post.service.PostService;
import com.example.simpleblogproject.domain.security.userDetails.UserDetailsImpl;
import com.example.simpleblogproject.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CommonResponse> addPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @Valid @ModelAttribute AddPostRequestDto postRequestDto) throws UnsupportedEncodingException {
        CommonResponse commonResponse = postService.addPost(userDetails.getUser().getId(), postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    // 전체 조회는 회원이 아니더라도 조회가 가능해야한다고 생각해서 token값 확인 X
    @GetMapping("/total")
    public ResponseEntity<List<GetTotalPostsResponseDto>> getTotalPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ){
        List<GetTotalPostsResponseDto> getTotalPostsResponseDto = postService.getTotalPosts(page - 1, size, sortBy, isAsc);
        return ResponseEntity.status(HttpStatus.OK).body(getTotalPostsResponseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<GetPostResponseDto> getPost(@PathVariable Long postId){
        GetPostResponseDto getPostResponseDto = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(getPostResponseDto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<CommonResponse> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @PathVariable Long postId,
                                                     @ModelAttribute UpdatePostRequestDto requestDto) throws UnsupportedEncodingException {
        CommonResponse commonResponse = postService.updatePost(userDetails.getUser().getId(), postId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
