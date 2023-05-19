package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchDailyTravelNumberRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TouristProductService {
    public ResponseEntity<? super GetTouristProductFormResponseDto> getTouristProductForm(UserToken userToken);
    public ResponseEntity<ResponseDto> postTouristProduct(UserToken userToken, PostTouristProductRequestDto dto);
    public ResponseEntity<? super GetTouristProductResponseDto> getTouristProduct(UserToken userToken, Integer productNumber);
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(UserToken userToken);
    public ResponseEntity<ResponseDto> patchTouristProduct(UserToken userToken, PatchTouristProductRequestDto dto, PatchDailyTravelNumberRequestDto deleteDailyTravelDto);
}
