package com.travelproject.travelproject.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class SignUpRequestDto {

    @NotBlank
    @Length(max = 10)
    private String userName;

    @NotBlank
    private String userBirth;

    @NotBlank
    @Email
    @Length(max = 40)
    private String userEmail;

    @NotBlank
    @Length(min = 8, max = 20)
    private String userPassword;

    @NotBlank
    @Length(min = 8, max = 20)
    private String userPasswordCheck;

    @NotBlank
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다.")
    private String userPhonenumber;


    
}
