package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.main.SignInRequestDto;
import com.travelproject.travelproject.dto.response.admin.main.SignInResponseDto;



public interface MainService {
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
