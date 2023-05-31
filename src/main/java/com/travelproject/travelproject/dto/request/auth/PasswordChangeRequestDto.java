package com.travelproject.travelproject.dto.request.auth;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PasswordChangeRequestDto {
    
    @NotBlank
    @Length(min = 8, max = 20)
    private String oldPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String newPasswordCheck;

}
