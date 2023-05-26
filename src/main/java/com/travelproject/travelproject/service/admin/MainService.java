package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.main.PostSignInRequestDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentTotalSaleResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetUserListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.PostSignInResponseDto;
import com.travelproject.travelproject.provider.UserToken;



public interface MainService {
    public ResponseEntity<? super PostSignInResponseDto> signIn(PostSignInRequestDto dto);
    public ResponseEntity<? super GetUserListResponseDto> getUserList(UserToken userToken);
    public ResponseEntity<? super GetPaymentTotalSaleResponseDto> getPaymentTotalSale(UserToken userToken);
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(UserToken userToken);
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(UserToken userToken);
}
