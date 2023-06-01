package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.auth.PatchPasswordChangeRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface PasswordChangeService {
    
    public ResponseEntity<ResponseDto> changePassword(UserToken userToken, PatchPasswordChangeRequestDto dto);

}
