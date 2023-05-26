package com.travelproject.travelproject.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.admin.user.GetUserListResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(RequestPattern.ADMIN_USER_API)
public class AdminUserController {
    private final UserService userService;

    private final String GET_USER_LIST ="list";

    @GetMapping(GET_USER_LIST)
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(userToken);
        return response;
    }
}
