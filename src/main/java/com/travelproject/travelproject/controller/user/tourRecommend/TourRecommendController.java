package com.travelproject.travelproject.controller.user.tourRecommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourListResponseDto;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourResponseDto;
import com.travelproject.travelproject.service.TourRecommendService;

@RestController
@RequestMapping(RequestPattern.RECOMMEND_API)
public class TourRecommendController {

    private TourRecommendService tourRecommendService;

    @Autowired
    public TourRecommendController(TourRecommendService tourRecommendService) {
        this.tourRecommendService=tourRecommendService;
    }

    //# 추천 여행지 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendList() {
        ResponseEntity<? super GetRecommendTourListResponseDto> response =
            tourRecommendService.getRecommendList();
        return response;
    }
    
    //# 추천 여행지 상세 조회
    @GetMapping("/{touristSpotNumber}")
    public ResponseEntity<? super GetRecommendTourResponseDto> getRecommend(
        @PathVariable("touristSpotNumber") Integer touristSpotNumber
    ) {
        ResponseEntity<? super GetRecommendTourResponseDto> response =
            tourRecommendService.getRecommend(touristSpotNumber);
        return response;
    }

    //# 지역, 이름 기준 추천 여행지 조회
    @GetMapping("/list/{writeRegion}/{writeRecommendSpotName}")
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendWriteList(
        @PathVariable("writeRegion") String writeRegion,
        @PathVariable("writeRecommendSpotName") String writeRecommendSpotName
    ) {
        ResponseEntity<? super GetRecommendTourListResponseDto> response =
            tourRecommendService.getRecommendWriteList(writeRegion, writeRecommendSpotName);
        return response;
    }
}
