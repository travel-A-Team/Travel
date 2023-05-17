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
import com.travelproject.travelproject.dto.request.admin.touristSpot.PacthTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.TouristSpotService;

@RestController
@RequestMapping(RequestPattern.ADMIN_TOURIST_SPOT_API)
public class TouristSpotController {

    private TouristSpotService touristSpotService;
    private final String POST_TOURIST_SPOT = "";
    private final String GET_TOURIST_SPOT = "/{writeTouristSpotNumber}";
    private final String GET_TOURIST_SPOT_LIST = "list";
    private final String PACTH_TOURIST_SPOT = "";
    private final String DELETE_TOURIST_SPOT = "/{writeTouristSpotNumber}";

    @Autowired
    public TouristSpotController(TouristSpotService touristSpotService) {
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

    //* 특정 여행지  조회
    @GetMapping(GET_TOURIST_SPOT)
    public ResponseEntity<? super GetTouristSpotResponseDto> getTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("writeTouristSpotNumber") Integer writeTouristSpotNumber
    ) {
        ResponseEntity<? super GetTouristSpotResponseDto> response = touristSpotService.getTouristSpot(userToken, writeTouristSpotNumber);
        return response;
    }

    //* 여행지 목록 조회    
    @GetMapping(GET_TOURIST_SPOT_LIST)
    public ResponseEntity<? super GetTouristSpotListResponseDto> getTouristSpotList
    (
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetTouristSpotListResponseDto> response =  touristSpotService.getTouristSpotList(userToken);
        return response;
    }

    //* 특정 여행지 수정
    @PatchMapping(PACTH_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> patchTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PacthTouristSpotRequestDto request 
    ) {
        ResponseEntity<ResponseDto> response = touristSpotService.pacthTouristSpot(userToken, request);
        return response;
    }

    //* 특정 여행지 삭제
    @DeleteMapping(DELETE_TOURIST_SPOT)
    public ResponseEntity<ResponseDto> deleteTouristSpot(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("writeTouristSpotNumber") Integer writeTouristSpotNumber
    ) {
        ResponseEntity<ResponseDto> response = touristSpotService.deleteTouristSpot(userToken, writeTouristSpotNumber);
        return response;
    }

}
