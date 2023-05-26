package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.admin.payment.GetPaymentListResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface PaymentService {
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentLIst(UserToken userToken);
}
