package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.auth.PatchPasswordChangeRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;

public interface PasswordChangeService {
    
    public ResponseEntity<ResponseDto> changePassword(String email, PatchPasswordChangeRequestDto dto);

}