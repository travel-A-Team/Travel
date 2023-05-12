package com.travelproject.travelproject.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.adminTouristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.TouristSpotService;

@RestController
@RequestMapping(RequestPattern.ADMIN_TOURIST_SPOT_API)
public class TouristSpotControlle {

    private TouristSpotService touristSpotService;
    private final String POST_TOURIST_SPOT = "";

    @Autowired
    public TouristSpotControlle(TouristSpotService touristSpotService) {
        this.touristSpotService = touristSpotService;
    }

    //* 여행지 작성
    @PostMapping(POST_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> postTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostTouristSpotRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = touristSpotService.postTouristSpot(userToken, request);
        return response;
    }
}
