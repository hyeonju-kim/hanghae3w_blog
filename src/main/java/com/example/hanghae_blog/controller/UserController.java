package com.example.hanghae_blog.controller;

import com.example.hanghae_blog.dto.LoginRequestDto;
import com.example.hanghae_blog.dto.LoginResponseDto;
import com.example.hanghae_blog.dto.SignupRequestDto;
import com.example.hanghae_blog.dto.SignupResponseDto;
import com.example.hanghae_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    /*
        HYUNSOO
        DTO는 왜쓸까?
        <DATA -> ENTITY>
        회원가입을 시켰다. -> <JSON> ->
     */
    // 회원가입
    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new SignupResponseDto("회원가입 성공", 200);
    }
    // 로그인
    @ResponseBody
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new LoginResponseDto("로그인 성공", 200);
    }
}
