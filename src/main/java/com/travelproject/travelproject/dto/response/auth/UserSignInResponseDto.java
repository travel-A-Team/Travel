package com.travelproject.travelproject.dto.response.auth;

import javax.validation.constraints.NotBlank;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserSignInResponseDto extends ResponseDto{
    
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;

    private String token;
    private int expirationDate;
    
    public UserSignInResponseDto(UserEntity userEntity, String token) {
        super("SU", "Success");
        this.userEmail = userEntity.getEmail();
        this.userPassword = userEntity.getPassword();
        this.token = token;
        this.expirationDate = 3600;
    }

}
