package com.example.hanghae_blog.dto;///

import com.example.hanghae_blog.entity.Comment;
import com.example.hanghae_blog.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {
	private Long id;
	private String title;
	private String content;
	private String username;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private List<CommentResponseDto> commentList;

	public PostResponseDto (Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.username = post.getUsername();
		this.createdAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
		//this.commentList = post.getCommentList();
	}
	public PostResponseDto (Post post, Comment comment) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.username = post.getUsername();
		this.createdAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
		//this.commentList = post.getCommentList();
	}
}
