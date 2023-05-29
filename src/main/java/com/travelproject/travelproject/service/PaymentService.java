package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.payment.PatchPaymentStatusRequestDto;
import com.travelproject.travelproject.dto.request.payment.PostPaymentRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.payment.GetRefundResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface PaymentService {
    public ResponseEntity<ResponseDto> postPayment(UserToken userToken, PostPaymentRequestDto dto);
    public ResponseEntity<? super GetRefundResponseDto> getRefund(UserToken userToken, String tid);
    public ResponseEntity<ResponseDto> patchRefund(UserToken userToken, PatchPaymentStatusRequestDto dto);
}
