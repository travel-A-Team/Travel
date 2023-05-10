package com.travelproject.travelproject.controller.adminController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.adminMain.SignInRequestDto;
import com.travelproject.travelproject.dto.response.adminMain.SignInResponseDto;
import com.travelproject.travelproject.service.AdminMainService;


@RestController
@RequestMapping(RequestPattern.ADMIN_MAIN_API)
public class AdminMainController {

    private AdminMainService adminMainService;
    
    public AdminMainController(AdminMainService adminMainService) {
        this.adminMainService = adminMainService;
    }

    @PostMapping("sign-in") 
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody SignInRequestDto request
    ) {
        ResponseEntity<? super SignInResponseDto> response = adminMainService.signIn(request);
        return response;
    }

}
