package com.travelproject.travelproject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.RecommendationTouristSpotService;

@RestController
@RequestMapping(RequestPattern.ADMIN_RECOMMENDATION_TOURIST_SPOT_API)
public class RecommendationTouristSpotController {

    private RecommendationTouristSpotService recommendationTouristSpotService;

    private final String POST_RECOMMENDATION_TOURIST_SPOT = "";

    @Autowired
    public RecommendationTouristSpotController(RecommendationTouristSpotService recommendationTouristSpotService) {
        this.recommendationTouristSpotService = recommendationTouristSpotService;
    }
    
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @RequestBody PostRecommendationTouristSpotRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = recommendationTouristSpotService.postRecommendationTouristSpot(userToken, request);
        return response;
    }
    
}
