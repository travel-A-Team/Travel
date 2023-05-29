package com.travelproject.travelproject.dto.response.admin.user;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserListResponseDto extends ResponseDto{
    
   private List<User> userList;
    
   public GetUserListResponseDto(List<UserEntity> userEntities) {
    
        super("SU", "Success");

        List<User> userList = new ArrayList<>();

        for (UserEntity userEntity: userEntities) {
            User user = new User(userEntity);
            userList.add(user);
        }
        this.userList = userList;
    }

}

@Data
@NoArgsConstructor
class User {
    private String name;
    private String email;
    private String birth;
    private String phoneNumber;
    private String registrationDatetime;

    public User(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.birth = userEntity.getBirth();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.registrationDatetime = userEntity.getRegisterDatetime();
    }
}
