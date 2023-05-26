package com.travelproject.travelproject.dto.request.admin.main;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSignInRequestDto {
    
    @NotBlank
    @Email
    private String adminEmail;

    @NotBlank
    private String adminPassword;
}
