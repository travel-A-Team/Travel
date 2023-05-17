package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PatchRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface RecommendationTouristSpotService {
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(UserToken userToken, PostRecommendationTouristSpotRequestDto dto);
    public ResponseEntity<? super GetRecommendationTouristSpotResponseDto> getRecommendationTouristSpot(UserToken userToken, Integer recommendTouristNumber);
    public ResponseEntity<? super GetRecommendationTouristSpotListResponseDto> getRecommendationTouristSpotList(UserToken userToken);
    public ResponseEntity<ResponseDto> patchRecommendationTouristSpot(UserToken userToken, PatchRecommendationTouristSpotRequestDto dto);
    public ResponseEntity<ResponseDto> deleteRecommendationTouristSpot(UserToken userToken, Integer recommendTouristNumber);
}
