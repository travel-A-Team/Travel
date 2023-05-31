package com.travelproject.travelproject.service;


import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.auth.GetFindEmailRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignInRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignUpRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.auth.SignInResponseDto;

public interface AuthService {
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    public ResponseEntity<ResponseDto> getUserEmail(GetFindEmailRequestDto requestBody);

}
