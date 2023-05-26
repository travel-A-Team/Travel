package com.travelproject.travelproject.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.admin.payment.GetPaymentListResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(RequestPattern.ADMIN_PAYMENT_API)
public class AdminPaymentController {

    private final PaymentService paymentService;

    private final String GET_PAYMENT_LIST = "list";

    @GetMapping(GET_PAYMENT_LIST)
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetPaymentListResponseDto> response = paymentService.getPaymentLIst(userToken);
        return response;
    }
}
