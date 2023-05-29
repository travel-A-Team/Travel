package com.travelproject.travelproject.controller.user.payment;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.dto.request.payment.PatchPaymentStatusRequestDto;
import com.travelproject.travelproject.dto.request.payment.PostPaymentRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.payment.GetRefundResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> postPayment(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostPaymentRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = paymentService.postPayment(userToken, request);
        return response;
    }

    @GetMapping("refund/{transaction-id}")
    public ResponseEntity<? super GetRefundResponseDto> getRefund(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("transaction-id") String transactionId
    ) {
        ResponseEntity<? super GetRefundResponseDto> response = paymentService.getRefund(userToken, transactionId);
        return response;
    }

    @PatchMapping("refund-status")
    public ResponseEntity<ResponseDto> patchRefund(
        @AuthenticationPrincipal UserToken userToken,
        @RequestBody PatchPaymentStatusRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = paymentService.patchRefund(userToken, request);
        return response;
    }
}
