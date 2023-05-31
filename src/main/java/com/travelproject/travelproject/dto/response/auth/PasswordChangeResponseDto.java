package com.travelproject.travelproject.dto.response.auth;


import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PasswordChangeResponseDto extends ResponseDto {
    
    private String message;

    public PasswordChangeResponseDto(PasswordChangeResponseDto dto) {
        super("SU", "Success");

    }
}
