package com.travelproject.travelproject.service.Implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseListResponseDto;
import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseResponseDto;
import com.travelproject.travelproject.dto.response.tourCourse.GetTourCourseWriteResponseDto;
import com.travelproject.travelproject.service.TourCourseService;

@Service
public class TourCourseImplementService implements TourCourseService {

    //! 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTourCourseListResponseDto> getTourCourseList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTourCourseList'");
    }

    //! 상품 상세 조회
    @Override
    public ResponseEntity<? super GetTourCourseResponseDto> getTourCourse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTourCourse'");
    }

    //! 지역, 이름 기준 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTourCourseWriteResponseDto> getTourCourseWrite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTourCourseWrite'");
    }
    
}
