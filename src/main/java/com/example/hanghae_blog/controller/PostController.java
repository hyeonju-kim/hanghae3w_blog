package com.example.hanghae_blog.controller;///

import com.example.hanghae_blog.dto.PostRequestDto;
import com.example.hanghae_blog.dto.PostResponseDto;
import com.example.hanghae_blog.entity.Post;
import com.example.hanghae_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PostController {
	private final PostService postService;

	// 1. 게시글 생성
	@PostMapping("/api/post")
	public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
		return postService.createPost(requestDto);
	}

	// 2. 게시글 전체 목록 조회
	@GetMapping("/api/post")
	public List<Post> getPostList() {
		return postService.getPostList();
	}

	// 3. 선택한 게시글 조회
	@GetMapping("/api/post/{id}")
	public PostResponseDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	// 4. 선택한 게시글 수정
	@PutMapping("/post/{id}")
	public PostResponseDto update(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
		return postService.update(id, requestDto);
	}

	// 5. 선택한 게시글 삭제
	@DeleteMapping("/post/{id}")
	public String delete(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
//		return new PostDeleteDto(postService.delete(id, requestDto));
		return postService.delete(id, requestDto);
	}
}
