package com.travelproject.travelproject.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchDailyTravelNumberRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.TouristProductService;

@RestController
@RequestMapping(RequestPattern.ADMIN_TOURIST_PRODUCT_API)
public class TouristProductController {
    private TouristProductService touristProductService;

    private final String GET_TOURIST_PRODUCT_FORM = "form";
    private final String POST_TOURIST_PRODUCT = "";
    private final String GET_TOURIST_PRODUCT = "/{productNumber}";
    private final String GET_TOURIST_PRODUCT_LIST = "list";
    private final String PATCH_TOURIST_PRODUCT = "";
    @Autowired
    public TouristProductController(TouristProductService touristProductService) {
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


    @GetMapping(GET_TOURIST_PRODUCT)
    public ResponseEntity<? super GetTouristProductResponseDto> getTouristProduct (
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("productNumber") Integer productNumber
    ) {
        ResponseEntity<? super GetTouristProductResponseDto> response = touristProductService.getTouristProduct(userToken, productNumber);
        return response;
    }

    @GetMapping(GET_TOURIST_PRODUCT_LIST)
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList (
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetTouristProductListResponseDto> response = touristProductService.getTouristProductList(userToken);
        return response;
    }

    @PatchMapping(PATCH_TOURIST_PRODUCT)
    public ResponseEntity<ResponseDto> patchTouristProduct (
        @AuthenticationPrincipal UserToken userToken,
        @RequestBody PatchTouristProductRequestDto patchRequest,
        @RequestBody PatchDailyTravelNumberRequestDto deleteRequest
    ) {
        ResponseEntity<ResponseDto> response = touristProductService.patchTouristProduct(userToken, patchRequest, deleteRequest);
        return response;
    }
}
