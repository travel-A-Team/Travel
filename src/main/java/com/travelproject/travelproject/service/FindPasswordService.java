package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.auth.PatchFindPasswordRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;

public interface FindPasswordService {
    
   public ResponseEntity<ResponseDto> postPassword(PatchFindPasswordRequestDto dto);
}
