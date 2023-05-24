package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.questionBoard.PostProductLikeyRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductWriteResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TourCourseService {
    
    //! 상품 목록 조회
    public ResponseEntity<? super GetTouristProductListResponseDto> getTourCourseList();
    
    //! 상품 상세 조회
    public ResponseEntity<? super GetTouristProductResponseDto> getTourCourse(Integer productBoardNumber);
    
    //! 지역, 이름 기준 상품 목록 조회
    public ResponseEntity<? super GetTouristProductWriteResponseDto> getTourCourseWriteList(String writeTouristSpotName);
    
    //! 좋아요 누르기
    public ResponseEntity<ResponseDto> postTourCourseLikey(UserToken userToken, PostProductLikeyRequestDto dto);

    //! 좋아요 취소
    public ResponseEntity<ResponseDto> deleteTourCourseLikey(UserToken userToken, Integer productBoardNumber);
}
