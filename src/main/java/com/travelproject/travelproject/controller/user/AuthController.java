package com.travelproject.travelproject.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.auth.GetFindEmailRequestDto;
import com.travelproject.travelproject.dto.request.auth.PatchFindPasswordRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignInRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignUpRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.auth.SignInResponseDto;
import com.travelproject.travelproject.service.AuthService;
import com.travelproject.travelproject.service.FindPasswordService;

@RestController
@RequestMapping(RequestPattern.AUTH_API)
public class AuthController {
    
    private AuthService authService;
    private FindPasswordService findPasswordService;

    @Autowired
    public AuthController(AuthService authService, FindPasswordService findPasswordService) {
        this.authService = authService;
        this.findPasswordService = findPasswordService;
    }

    //* 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
        @Valid @RequestBody SignUpRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    //* 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @Valid @RequestBody SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
    //* 이메일 찾기
    @GetMapping("/find-email")
    public ResponseEntity<? super ResponseDto> getUserEmail(
        @Valid @RequestBody GetFindEmailRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.getUserEmail(requestBody);
        return response;
    }

    //* 비밀번호 찾기
    @PatchMapping("/find-password")
    public ResponseEntity<ResponseDto> findPassword(
        @Valid @RequestBody PatchFindPasswordRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = findPasswordService.postPassword(requestBody);
        return response;
    }
}
