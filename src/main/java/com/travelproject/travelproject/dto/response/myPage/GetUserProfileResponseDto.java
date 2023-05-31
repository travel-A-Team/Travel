package com.travelproject.travelproject.dto.response.myPage;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class GetUserProfileResponseDto extends ResponseDto {
    
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
    
    public GetUserProfileResponseDto(UserEntity userEntity) {
        super("SU", "Success");
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.birth = userEntity.getBirth();
        this.phoneNumber = userEntity.getPhoneNumber();
    }
}
