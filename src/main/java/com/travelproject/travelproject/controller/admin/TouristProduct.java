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
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.TouristProductService;

@RestController
@RequestMapping(RequestPattern.ADMIN_TOURIST_PRODUCT_API)
public class TouristProduct {
    private TouristProductService touristProductService;

    private final String GET_TOURIST_PRODUCT_FORM = "form";
    private final String POST_TOURIST_PRODUCT = "";

    @Autowired
    public TouristProduct(TouristProductService touristProductService) {
        this.touristProductService = touristProductService;
    }

    @GetMapping(GET_TOURIST_PRODUCT_FORM)
    public ResponseEntity<? super GetTouristProductFormResponseDto> getTouristProductForm (
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetTouristProductFormResponseDto> response = touristProductService.getTouristProductForm(userToken);
        return response;
    }

    @PostMapping(POST_TOURIST_PRODUCT)
    public ResponseEntity<ResponseDto> postTouristProduct (
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostTouristProductRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = touristProductService.postTouristProduct(userToken, request);
        return response;
    }



}
