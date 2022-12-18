package com.example.hanghae_blog.service;///

import com.example.hanghae_blog.dto.CommentResponseDto;
import com.example.hanghae_blog.dto.DeleteReponseDto;
import com.example.hanghae_blog.dto.PostRequestDto;
import com.example.hanghae_blog.dto.PostResponseDto;
import com.example.hanghae_blog.entity.Comment;
import com.example.hanghae_blog.entity.Post;
import com.example.hanghae_blog.entity.User;
import com.example.hanghae_blog.jwt.JwtUtil;
import com.example.hanghae_blog.repository.CommentRepository;
import com.example.hanghae_blog.repository.PostRepository;
import com.example.hanghae_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
//    private final User user;
    private final JwtUtil jwtUtil;

    // 1. 토큰 있는 경우에만 게시글 생성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
          // 사용자의 정보 가져오기. request에서 Token가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        // 토큰이 있는 경우에만 글 작성 가능
        if (token != null) {
            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("로그인 해주세요")
            );
            Post post = new Post(requestDto, user.getUsername());
            post.setUser(user); // 추가함. 윗줄보다 이렇게 추가하는게 낫다.
            // user.add(post); // User에서 만든 add메서드를 이렇게 추가해도 된다!!
            postRepository.save(post);
            return new PostResponseDto(post);
        }else {
            return null;
        }
    }
    // 2. 게시글 전체 목록 조회 (Dto로 반환? Post로 반환?) -> Dto로 !
    @Transactional
    public List<PostResponseDto> getPostList() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc(); // List<Post> 꺼내오기
        if(Collections.isEmpty(posts)) return null;
        List<PostResponseDto> results = new ArrayList<>(); // List<PostResponseDto> 빈통 만들기 (주연)

        for(Post post : posts) {
            List<Comment> comments = commentRepository.findAllByPosts(post); // List<Comment> 꺼내오기 (코멘트 조연)
            PostResponseDto postResponseDto = new PostResponseDto(post); // PostResponseDto 빈통 만들기 (조연)
            List<CommentResponseDto> commentResponseDtos = new ArrayList<>(); // List<CommentResponseDto> 빈통 만들기(코멘트 주연)
            for (Comment comment : comments) {
                commentResponseDtos.add(new CommentResponseDto(comment)); // List<CommentResponseDto>에 CommnetResponseDto를 add하기
            }
            postResponseDto.setCommentList(commentResponseDtos); // postResponseDto 에 CommentList 세팅하기
            results.add(postResponseDto); // List<PostResponseDto> 에 postResponseDto를 add 하기
        }
        return results;
    }

    // 3. 선택한 게시글 조회 -> 예외처리("게시글이 존재하지 않습니다")
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow( // Post 꺼내오기
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        PostResponseDto postDto = new PostResponseDto(post); // PostResponseDto 빈통 만들기 (주연)

        List<Comment> comments = commentRepository.findAllByPosts(post);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDtos.add(new CommentResponseDto(comment));
        }
        postDto.setCommentList(commentResponseDtos);
        return postDto;
    }

    //4. 토큰이 있는 경우만 게시글 수정 -> ("아이디가 존재하지 않습니다")
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기(claims)
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
            );
            post.update(requestDto);
            postRepository.save(post);
            return new PostResponseDto(post);
        }else {
            return null;
        }
    }
     //5. 토큰이 있는 경우만 게시글 삭제
    @Transactional
    public DeleteReponseDto delete(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            }else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("로그인 해주세요")
            );
            postRepository.deleteById(id);
            String response = "삭제완료!";
            return new DeleteReponseDto(response);
        }
        return null;
    }
}
