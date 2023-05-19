package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductWriteResponseDto;

public interface TourCourseService {
    
    //! 상품 목록 조회
    public ResponseEntity<? super GetTouristProductListResponseDto> getTourCourseList();
    
    //! 상품 상세 조회
    public ResponseEntity<? super GetTouristProductResponseDto> getTourCourse(Integer productBoardNumber);
    
    //! 지역, 이름 기준 상품 목록 조회
    public ResponseEntity<? super GetTouristProductWriteResponseDto> getTourCourseWriteList(String writeTouristSpotName);
    
}
