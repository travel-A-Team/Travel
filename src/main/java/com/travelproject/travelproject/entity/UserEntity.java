package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.auth.GetFindEmailRequestDto;
import com.travelproject.travelproject.dto.request.auth.PatchPasswordChangeRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignUpRequestDto;
import com.travelproject.travelproject.dto.response.myPage.GetUserProfileResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {

    @Id
    private String email;
    private String name;
    private String birth;
    private String password;
    private String phoneNumber;
    private String registerDatetime;

    public UserEntity(SignUpRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.registerDatetime = simpleDateFormat.format(now);
        this.email = dto.getUserEmail();
        this.name = dto.getUserName();
        this.password = dto.getUserPassword();
        this.birth = dto.getUserBirth();
        this.phoneNumber = dto.getUserPhonenumber();
    }


    public UserEntity(GetFindEmailRequestDto dto) {
        this.name = dto.getUserName();
        this.phoneNumber = dto.getUserPhonenumber();
    }

    public UserEntity(GetUserProfileResponseDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.birth = dto.getBirth();
        this.phoneNumber = dto.getPhoneNumber();
    }
    
}