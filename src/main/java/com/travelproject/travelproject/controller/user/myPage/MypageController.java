package com.travelproject.travelproject.controller.user.myPage;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.dto.response.myPage.GetPaymentListResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.MyPageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/mypage")
public class MypageController {

    private final MyPageService myPageService;

    @GetMapping("payment-list")
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetPaymentListResponseDto> response = myPageService.getPaymentList(userToken);
        return response;
    }
}
