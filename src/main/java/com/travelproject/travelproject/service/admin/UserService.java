package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.admin.user.GetUserListResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface UserService {
    public ResponseEntity<? super GetUserListResponseDto> getUserList(UserToken userToken);
}
