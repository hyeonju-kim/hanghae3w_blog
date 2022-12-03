package com.example.hanghae_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanghaeBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanghaeBlogApplication.class, args);
	}

}
