package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.touristSpot.PacthTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TouristSpotService {
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, PostTouristSpotRequestDto dto);
    public ResponseEntity<? super GetTouristSpotResponseDto> getTouristSpot(UserToken userToken, Integer writeTouristSpotNumber);
    public ResponseEntity<? super GetTouristSpotListResponseDto> getTouristSpotList(UserToken userToken);
    public ResponseEntity<ResponseDto> pacthTouristSpot(UserToken userToken, PacthTouristSpotRequestDto dto);
    public ResponseEntity<ResponseDto> deleteTouristSpot(UserToken userToken, Integer writeTouristSpotNumber);
}
