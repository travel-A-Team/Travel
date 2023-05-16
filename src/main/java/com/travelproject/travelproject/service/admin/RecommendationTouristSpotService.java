package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface RecommendationTouristSpotService {
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(UserToken userToken, PostRecommendationTouristSpotRequestDto dto);
}
