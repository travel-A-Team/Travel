package com.travelproject.travelproject.controller.user.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.auth.PasswordChangeRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.service.PasswordChangeService;

@RestController
@RequestMapping(RequestPattern.MY_PAGE_API)
public class PasswordChangeController {

    private PasswordChangeService passwordChangeService;

    @Autowired
    public PasswordChangeController(PasswordChangeService passwordChangeService) {
        this.passwordChangeService = passwordChangeService;
    }
    
    //* 마이페이지 프로필에서 비밀번호 변경
    @PutMapping("/change-password")
    public ResponseEntity<ResponseDto> changePassword(
        @RequestParam("email") String email,
        @RequestBody PasswordChangeRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = passwordChangeService.changePassword(email, requestBody);

        return response;
        }
}