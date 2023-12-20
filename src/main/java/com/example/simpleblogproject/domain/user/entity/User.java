package com.example.simpleblogproject.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(length = 40)
    private String email;

    @Builder
    public User(String nickname, String password, String email){
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }
}
