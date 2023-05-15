package com.travelproject.travelproject.common.util;

import com.travelproject.travelproject.provider.UserToken;

public class UserTokenAdminRoleValidation {
    public static boolean adminRoleValidation(UserToken userToken) {
        String role = userToken.getRole();

        boolean roleValidation = role.equals("admin");
        
        return roleValidation;
    }
}
