package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchRegionAndTouristSpotNameResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchRegionResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchTouristSpotNameResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TouristProductService {
    public ResponseEntity<? super GetTouristProductFormResponseDto> getTouristProductForm(UserToken userToken);
    public ResponseEntity<ResponseDto> postTouristProduct(UserToken userToken, PostTouristProductRequestDto dto);
    public ResponseEntity<? super GetTouristProductResponseDto> getTouristProduct(UserToken userToken, Integer productNumber);
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(UserToken userToken);
    public ResponseEntity<ResponseDto> patchTouristProduct(UserToken userToken, PatchTouristProductRequestDto dto);
    public ResponseEntity<ResponseDto> deleteTouristProduct(UserToken userToken, Integer productNumber);
    public ResponseEntity<? super GetSearchRegionResultResponseDto> getSearchRegionResult(UserToken userToken, String region);
    public ResponseEntity<? super GetSearchTouristSpotNameResultResponseDto> getSearchTouristSpotNameResult(UserToken userToken, String touristSpotName);
    public ResponseEntity<? super GetSearchRegionAndTouristSpotNameResultResponseDto> getSearchRegionAndTouristSpotNameResult(UserToken userToken, String region, String touristSpotName);

}
