package com.example.hanghae_blog.service;///

import com.example.hanghae_blog.dto.PostRequestDto;
import com.example.hanghae_blog.dto.PostResponseDto;
import com.example.hanghae_blog.entity.Post;
import com.example.hanghae_blog.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 1. 게시글 생성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    // 2. 게시글 전체 목록 조회 (Dto로 반환? Post로 반환?)
    @Transactional
    public List<Post> getPostList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 3. 선택한 게시글 조회 -> 예외처리("게시글이 존재하지 않습니다")
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    // 4. 선택한 게시글 수정 -> ("아이디가 존재하지 않습니다")
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());
        if (result) {
            post.update(requestDto);
        }
        return new PostResponseDto(post);
    }

    // 5. 선택한 게시글 삭제
    @Transactional
    public String delete(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());
        String response;
        if(result){
            postRepository.deleteById(id);
            response = "삭제완료!";
        }else {
            response = "비밀번호가 일치하지 않습니다";
        }
        return response;
    }
}
