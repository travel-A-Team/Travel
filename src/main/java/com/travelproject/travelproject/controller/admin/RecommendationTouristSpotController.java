package com.travelproject.travelproject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.RecommendationTouristSpotService;

@RestController
@RequestMapping(RequestPattern.ADMIN_RECOMMENDATION_TOURIST_SPOT_API)
public class RecommendationTouristSpotController {

    private RecommendationTouristSpotService recommendationTouristSpotService;

    private final String POST_RECOMMENDATION_TOURIST_SPOT = "";
    private final String GET_RECOMMENDATION_TOURIST_SPOT = "/{recommendTouristNumber}";

    @Autowired
    public RecommendationTouristSpotController(RecommendationTouristSpotService recommendationTouristSpotService) {
        this.recommendationTouristSpotService = recommendationTouristSpotService;
    }
    

    //* 추천 여행지 작성
    @PostMapping(POST_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @RequestBody PostRecommendationTouristSpotRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = recommendationTouristSpotService.postRecommendationTouristSpot(userToken, request);
        return response;
    }

    //* 특정 여행지 조회
    @GetMapping(GET_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<? super GetRecommendTourResponseDto> getRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("recommendTouristNumber") Integer recommendTouristNumber
    ) {
        ResponseEntity<? super GetRecommendTourResponseDto> response = recommendationTouristSpotService.getRecommendationTouristSpot(userToken, recommendTouristNumber);
        return response;
    }
    
}
