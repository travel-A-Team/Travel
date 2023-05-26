package com.travelproject.travelproject.dto.response.admin.main;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignInResponseDto extends ResponseDto{
    
    private String token;
    private int expirationDate;

  
    public PostSignInResponseDto(String token) {
        super("SU", "Success");
        this.token = token;
        this.expirationDate = 3600;
    }


}
