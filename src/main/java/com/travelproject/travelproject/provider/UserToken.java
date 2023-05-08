package com.travelproject.travelproject.provider;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserToken {
    private String email;
    private String role;
}
