package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.myPage.GetPaymentListResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface MyPageService {
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(UserToken userToken);
}
