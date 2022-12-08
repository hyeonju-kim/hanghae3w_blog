package com.example.hanghae_blog.controller;

import com.example.hanghae_blog.dto.*;
import com.example.hanghae_blog.service.CommentService;
import com.example.hanghae_blog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {
    private final CommentService commentService;

    // 1. 댓글 생성
    @PostMapping("/api/comment/{id}")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createComment(requestDto, request);
    }
    // 2. 댓글 수정
    @PutMapping("/api/comment/{id}")
    public CommentResponseDto update(@RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.update(requestDto, request);
    }
    // 3. 댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public CommentDeleteDto delete(@RequestBody HttpServletRequest request) {
        return commentService.delete(request);
    }
}