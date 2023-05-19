package com.travelproject.travelproject.controller.user.tourCourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductWriteResponseDto;
import com.travelproject.travelproject.service.TourCourseService;

@RestController
@RequestMapping(RequestPattern.PRODUCT_API)
public class TourCourseController {

    private TourCourseService tourCourseService;

    @Autowired
    public TourCourseController(TourCourseService tourCourseService) {
        this.tourCourseService=tourCourseService;
    }
    
    //# 상품 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetTouristProductListResponseDto> getTourCourseList() {
        ResponseEntity<? super GetTouristProductListResponseDto> response =
            tourCourseService.getTourCourseList();
        return response;
    }
    
    //# 특정 상품 상세 조회
    @GetMapping("/{productBoardNumber}")
    public ResponseEntity<? super GetTouristProductResponseDto> getTourCourse(
        @PathVariable("productBoardNumber") Integer productBoardNumber
    ) {
        ResponseEntity<? super GetTouristProductResponseDto> response =
            tourCourseService.getTourCourse(productBoardNumber);
        return response;
    }
    
    //# 이름 기준 상품 목록 조회
    @GetMapping("/list/{writeTouristSpotName}")
    public ResponseEntity<? super GetTouristProductWriteResponseDto> getTourCourseWriteList(
        @PathVariable("writeTouristSpotName") String writeTouristSpotName
    ) {
        ResponseEntity<? super GetTouristProductWriteResponseDto> response =
            tourCourseService.getTourCourseWriteList(writeTouristSpotName);
        return response;    
    }
}
