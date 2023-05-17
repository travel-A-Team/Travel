package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TouristProductService {
    public ResponseEntity<? super GetTouristProductFormResponseDto> getTouristProductForm(UserToken userToken);
}
