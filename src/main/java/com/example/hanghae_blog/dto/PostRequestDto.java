package com.example.hanghae_blog.dto;///

import io.jsonwebtoken.Claims;
import lombok.Getter;


@Getter
public class PostRequestDto {
	private String title;
	private String content;
}
