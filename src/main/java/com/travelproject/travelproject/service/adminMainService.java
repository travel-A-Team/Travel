package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.adminMain.SignInRequestDto;
import com.travelproject.travelproject.dto.response.adminMain.SignInResponseDto;

public interface AdminMainService {
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
