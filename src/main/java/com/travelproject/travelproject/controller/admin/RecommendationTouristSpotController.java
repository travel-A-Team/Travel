package com.travelproject.travelproject.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PatchRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.RecommendationTouristSpotService;

@RestController
@RequestMapping(RequestPattern.ADMIN_RECOMMENDATION_TOURIST_SPOT_API)
public class RecommendationTouristSpotController {

    private RecommendationTouristSpotService recommendationTouristSpotService;

    private final String POST_RECOMMENDATION_TOURIST_SPOT = "";
    private final String GET_RECOMMENDATION_TOURIST_SPOT = "/{recommendTouristNumber}";
    private final String GET_RECOMMENDATION_TOURIST_SPOT_LIST = "list";
    private final String PATCH_RECOMMENDATION_TOURIST_SPOT = "";
    private final String DELETE_RECOMMENDATION_TOURIST_SPOT = "/{recommendTouristNumber}";

    @Autowired
    public RecommendationTouristSpotController(RecommendationTouristSpotService recommendationTouristSpotService) {
        this.recommendationTouristSpotService = recommendationTouristSpotService;
    }
    

    //* 추천 여행지 작성
    @PostMapping(POST_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostRecommendationTouristSpotRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = recommendationTouristSpotService.postRecommendationTouristSpot(userToken, request);
        return response;
    }

    //* 특정  추천 여행지 조회
    @GetMapping(GET_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<? super GetRecommendationTouristSpotResponseDto> getRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("recommendTouristNumber") Integer recommendTouristNumber
    ) {
        ResponseEntity<? super GetRecommendationTouristSpotResponseDto> response = recommendationTouristSpotService.getRecommendationTouristSpot(userToken, recommendTouristNumber);
        return response;
    }
    
    //* 추천 여행지 목록 조회
    @GetMapping(GET_RECOMMENDATION_TOURIST_SPOT_LIST)
    public ResponseEntity<? super GetRecommendationTouristSpotListResponseDto> getRecommendationTouristSpotList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetRecommendationTouristSpotListResponseDto> response = recommendationTouristSpotService.getRecommendationTouristSpotList(userToken);
        return response;
    }

    //* 특정 추천 여행지 수정 
    @PatchMapping(PATCH_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> patchRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PatchRecommendationTouristSpotRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = recommendationTouristSpotService.patchRecommendationTouristSpot(userToken, request);
        return response;
    }

    @DeleteMapping(DELETE_RECOMMENDATION_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> deleteRecommendationTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("recommendTouristNumber") Integer recommendTouristNumber
    ) {
        ResponseEntity<ResponseDto> response = recommendationTouristSpotService.deleteRecommendationTouristSpot(userToken, recommendTouristNumber);
        return response;
    }

}
