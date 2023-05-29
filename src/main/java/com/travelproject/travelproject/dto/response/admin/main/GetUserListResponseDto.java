package com.travelproject.travelproject.dto.response.admin.main;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserListResponseDto extends ResponseDto{   
    private List<UserSummary> userList;

    public GetUserListResponseDto(List<UserEntity> userEntities) {
        super("SU", "Success");

        List<UserSummary> userList = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            UserSummary userSummary = new UserSummary(userEntity);
            userList.add(userSummary);
        }

        this.userList = userList;
    }
}
@Data
@NoArgsConstructor
class UserSummary {
    private String email;
    private String name;
    private String phoneNumber;
    private String registerDate;

    public UserSummary(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.registerDate = userEntity.getRegisterDatetime();
    }
}
