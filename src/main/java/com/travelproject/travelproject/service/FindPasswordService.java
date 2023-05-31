package com.travelproject.travelproject.service;

import com.travelproject.travelproject.dto.response.auth.FindPasswordResponseDto;

public interface FindPasswordService {
    
   public FindPasswordResponseDto resetPassword(String email, String phoneNumber);
}
