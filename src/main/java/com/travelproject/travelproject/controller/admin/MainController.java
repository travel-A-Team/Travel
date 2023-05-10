package com.travelproject.travelproject.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.adminMain.SignInRequestDto;
import com.travelproject.travelproject.dto.response.adminMain.SignInResponseDto;
import com.travelproject.travelproject.service.admin.MainService;


@RestController
@RequestMapping(RequestPattern.ADMIN_MAIN_API)
public class MainController {

    private MainService adminMainService;
    private final String POST_SIGN_IN = "sign-in";
    
    @Autowired
    public MainController(MainService adminMainService) {
        this.adminMainService = adminMainService;
    }

    @PostMapping(POST_SIGN_IN) 
    public ResponseEntity<? super SignInResponseDto> signIn(
        @Valid  @RequestBody SignInRequestDto request
    ) {
        ResponseEntity<? super SignInResponseDto> response = adminMainService.signIn(request);
        return response;
    }

}
