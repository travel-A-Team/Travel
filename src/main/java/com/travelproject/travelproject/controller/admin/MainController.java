package com.travelproject.travelproject.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.admin.main.PostSignInRequestDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentTotalSaleResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetUserListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.PostSignInResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.MainService;


@RestController
@RequestMapping(RequestPattern.ADMIN_MAIN_API)
public class MainController {

    private MainService adminMainService;
    private final String POST_SIGN_IN = "sign-in";
    private final String GET_USER_LIST = "user-list";
    private final String GET_PAYMENT_TOTAL_SALE = "payment/total-sale";
    private final String GET_PAYMENT_LIST = "payment-list";
    private final String GET_TOURIST_PRODUCT_LIST ="tourist-product-list";
    
    @Autowired
    public MainController(MainService adminMainService) {
        this.adminMainService = adminMainService;
    }

    @PostMapping(POST_SIGN_IN) 
    public ResponseEntity<? super PostSignInResponseDto> signIn(
        @Valid  @RequestBody PostSignInRequestDto request
    ) {
        ResponseEntity<? super PostSignInResponseDto> response = adminMainService.signIn(request);
        return response;
    }

    @GetMapping(GET_USER_LIST)
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = adminMainService.getUserList(userToken);
        return response;
    }

    @GetMapping(GET_PAYMENT_TOTAL_SALE)
    public ResponseEntity<? super GetPaymentTotalSaleResponseDto> getPaymentTotalSale(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetPaymentTotalSaleResponseDto> response = adminMainService.getPaymentTotalSale(userToken);
        return response;
    }

    @GetMapping(GET_PAYMENT_LIST)
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetPaymentListResponseDto> response = adminMainService.getPaymentList(userToken);
        return response;
    }

    @GetMapping(GET_TOURIST_PRODUCT_LIST) 
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetTouristProductListResponseDto> response = adminMainService.getTouristProductList(userToken);
        return response;
    }
}
