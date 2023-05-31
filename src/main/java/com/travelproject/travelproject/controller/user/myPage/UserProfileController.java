package com.travelproject.travelproject.controller.user.myPage;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.myPage.GetProductLikeResponseDto;
import com.travelproject.travelproject.dto.response.myPage.GetUserProfileResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.MyPageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(RequestPattern.MY_PAGE_API)
public class UserProfileController {
    
    private final MyPageService myPageService;

    @GetMapping("/profile")
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetUserProfileResponseDto> response = myPageService.getUserProfile(userToken);
        return response;
    }

    @GetMapping("/product-like-list")
    public ResponseEntity<? super GetProductLikeResponseDto> getLikeProduct(
        @AuthenticationPrincipal UserToken userToken
    ) {
        
        ResponseEntity<? super GetProductLikeResponseDto> response = myPageService.getLikeProduct(userToken);
        return response;
    }
}
