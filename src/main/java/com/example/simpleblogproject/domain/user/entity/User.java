package com.example.simpleblogproject.domain.user.entity;

import com.example.simpleblogproject.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    //Enum 타입을 데이터 베이스에 저장한다.
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Builder
    public User(String nickname, String password, UserRoleEnum role){
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public void addPost(Post post) {
        this.postList.add(post);
        Post.builder().user(this).build();
    }
}
