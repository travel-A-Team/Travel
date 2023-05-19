package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourListResponseDto;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourResponseDto;


public interface TourRecommendService {
    
    //! 추천 여행지 목록 조회
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendList();

    //! 추천 여행지 상세 조회
    public ResponseEntity<? super GetRecommendTourResponseDto> getRecommend(Integer touristSpotNumber);

    //! 지역, 이름 기준 추천 여행지 조회
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendWriteList(String writeRegion, String writeRecommendSpotName);

}
