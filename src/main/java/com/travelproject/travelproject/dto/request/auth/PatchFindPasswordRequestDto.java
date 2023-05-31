package com.travelproject.travelproject.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PatchFindPasswordRequestDto {
    
    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다.")
    private String userPhonenumber;
}
