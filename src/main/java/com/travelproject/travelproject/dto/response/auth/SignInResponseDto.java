package com.travelproject.travelproject.dto.response.auth;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto extends ResponseDto {
    
    private String token;
    private int expirationDate;


    public SignInResponseDto(UserEntity userEntity, String token) {
        super("SU", "Success");
        this.token = token;
        this.expirationDate = 3600;
    }

    public SignInResponseDto(String token) {
        super("SU", "Success");
        this.token = token;
        this.expirationDate = 3600;
    }
}
