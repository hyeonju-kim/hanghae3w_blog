package com.example.hanghae_blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comment> commentList = new ArrayList<>();


    // User를 post에 설정을 해줘야 DB에 연결이 된다.-> 왜 연결이 안되지????
//    public void add(Post post) {
//        post.setUser(this);
//        this.posts.add(post);
//    }

    public User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

