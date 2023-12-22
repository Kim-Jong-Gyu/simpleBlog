package com.example.simpleblogproject.domain.post.service;

import com.example.simpleblogproject.domain.comment.dto.GetCommentResponseDto;
import com.example.simpleblogproject.domain.comment.entity.Comment;
import com.example.simpleblogproject.domain.post.dto.AddPostRequestDto;
import com.example.simpleblogproject.domain.post.dto.GetPostResponseDto;
import com.example.simpleblogproject.domain.post.dto.GetTotalPostsResponseDto;
import com.example.simpleblogproject.domain.post.dto.UpdatePostRequestDto;
import com.example.simpleblogproject.domain.post.entity.Post;
import com.example.simpleblogproject.domain.post.repository.PostRepository;
import com.example.simpleblogproject.domain.s3.S3Service;
import com.example.simpleblogproject.domain.user.entity.User;
import com.example.simpleblogproject.domain.user.repository.UserRepository;
import com.example.simpleblogproject.global.common.CommonResponse;
import com.example.simpleblogproject.global.common.CommonResponseCode;
import com.example.simpleblogproject.global.exception.CustomException;
import com.example.simpleblogproject.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommonResponse addPost(Long id, AddPostRequestDto requestDto) throws UnsupportedEncodingException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionResponseCode.NOT_FOUND_USER)
        );
        String[] urlArr = new String[2];
        if (requestDto.getPicture() != null) {
            urlArr = s3Service.saveFile(user.getNickname(), requestDto.getPicture());
        }
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .pictureUrl(urlArr[0])
                .picturePath(urlArr[1])
                .countLike(0L)
                .user(user)
                .build();
        postRepository.save(post);
        user.addPost(post);
        return new CommonResponse(CommonResponseCode.ADD_POST_SUCCESS);
    }

    public List<GetTotalPostsResponseDto> getTotalPosts(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        return postRepository.findAll(pageable).map(GetTotalPostsResponseDto::new).getContent();
    }

    public GetPostResponseDto getPost(Long postId) {
        Post post = findById(postId);
        List<GetCommentResponseDto> getCommentResponseDtoList = post.getCommentList().stream().map(GetCommentResponseDto::new).toList();
        return GetPostResponseDto.builder()
                .nickname(post.getUser().getNickname())
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .commentList(getCommentResponseDtoList)
                .build();
    }

    @Transactional
    public CommonResponse updatePost(Long userId, Long postId, UpdatePostRequestDto requestDto) throws UnsupportedEncodingException {
        Post post = findById(postId);
        String[] urlArr = new String[2];
        if(!Objects.equals(post.getUser().getId(), userId)){
            throw new CustomException(ExceptionResponseCode.NOT_MATCH_USER_POST);
        }
        if (requestDto.getPicture() != null) {
            urlArr = s3Service.saveFile(post.getUser().getNickname(), requestDto.getPicture());
        }
        post.update(requestDto, urlArr);
        return new CommonResponse(CommonResponseCode.UPDATE_POST_SUCCESS);
    }

    // 현재시간을 기준으로 수정 날짜가 90일이 지난 경우 삭제하게 한다.
    // Test를 위해 시간을 5분으로 임시로 설정 -> Sql 폴더에 있는 Sql문을 통해 데이터를 미리 넣는다.
    @Scheduled(cron = "0 0/5 * * * ?")
    public void deletePost(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ninetyDaysAgo = now.minusDays(90);
        List<Post> postList = postRepository.findByModifiedAtLessThan(ninetyDaysAgo);
        if(!postList.isEmpty()){
            for(Post post : postList){
                s3Service.deleteImage(post.getPicturePath());
                postRepository.delete(post);
            }
        }
    }

    private Post findById(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(ExceptionResponseCode.NOT_FOUND_POST));
    }
}
