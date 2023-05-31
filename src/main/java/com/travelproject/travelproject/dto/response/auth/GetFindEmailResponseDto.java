package com.travelproject.travelproject.dto.response.auth;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class GetFindEmailResponseDto extends ResponseDto{
    
    private String userEmail;

    public GetFindEmailResponseDto(String email) {
        super("SU", "Success");
        this.userEmail = email;
    }
}
