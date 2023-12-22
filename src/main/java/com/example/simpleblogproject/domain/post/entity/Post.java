package com.example.simpleblogproject.domain.post.entity;

import com.example.simpleblogproject.domain.comment.entity.Comment;
import com.example.simpleblogproject.domain.post.dto.UpdatePostRequestDto;
import com.example.simpleblogproject.domain.user.entity.User;
import com.example.simpleblogproject.domain.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "posts")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(length = 1000)
    private String pictureUrl;

    // S3 삭제를 위한 Column
    @Column(length = 1000)
    private String picturePath;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // like는 예약어다.
    @Column
    private Long countLike = 0L;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(String title, String content, String pictureUrl, String picturePath, User user, Long countLike){
        this.title = title;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.picturePath = picturePath;
        this.user = user;
        this.countLike = countLike;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(UpdatePostRequestDto requestDto, String[] urlArr) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.pictureUrl = urlArr[0];
        this.picturePath = urlArr[1];
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
        Comment.builder().post(this).build();
    }

    public void addLike() {
        this.countLike+= 1;
    }
}
