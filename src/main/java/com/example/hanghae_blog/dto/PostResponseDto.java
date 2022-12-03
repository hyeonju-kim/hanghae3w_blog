package com.example.hanghae_blog.dto;

import com.example.hanghae_blog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public PostResponseDto (Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.author = post.getAuthor();
		this.content = post.getContent();
		this.createdAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
	}
}
