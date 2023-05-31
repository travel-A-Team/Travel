package com.travelproject.travelproject.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FindPasswordResponseDto {
    
    private boolean success;
    private String message;
    private String temporaryPassword;
}
