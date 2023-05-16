package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseListResponseDto;
import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseResponseDto;
import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseWriteResponseDto;

public interface TourCourseService {
    
    //! 상품 목록 조회
    public ResponseEntity<? super GetTourCourseListResponseDto> getTourCourseList();
    
    //! 상품 상세 조회
    public ResponseEntity<? super GetTourCourseResponseDto> getTourCourse();
    
    //! 지역, 이름 기준 상품 목록 조회
    public ResponseEntity<? super GetTourCourseWriteResponseDto> getTourCourseWrite();
    
}
