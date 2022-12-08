package com.example.hanghae_blog.repository;

import com.example.hanghae_blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
