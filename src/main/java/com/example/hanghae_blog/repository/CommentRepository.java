package com.example.hanghae_blog.repository;

import com.example.hanghae_blog.entity.Comment;
import com.example.hanghae_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findAllByPosts(Post post);
}
