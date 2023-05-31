package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.myPage.GetPaymentListResponseDto;
import com.travelproject.travelproject.dto.response.myPage.GetProductLikeResponseDto;
import com.travelproject.travelproject.dto.response.myPage.UserProfileResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface MyPageService {
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(UserToken userToken);
    // 유저 프로필 조회
    public ResponseEntity<? super UserProfileResponseDto> getUserProfile(UserToken userToken);

    public ResponseEntity<? super GetProductLikeResponseDto> getLikeProduct(UserToken userToken);
}
