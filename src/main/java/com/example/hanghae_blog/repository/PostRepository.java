package com.example.hanghae_blog.repository;///

import com.example.hanghae_blog.dto.PostResponseDto;
import com.example.hanghae_blog.entity.Post;
import com.example.hanghae_blog.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface 	PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByOrderByModifiedAtDesc();
	Optional<Post> findByUsername(String username);
}
