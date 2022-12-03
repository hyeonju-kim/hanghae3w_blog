package com.example.hanghae_blog.dto;//

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
	private String title;
	private String content;
	private String author;
	private String password;
}
