package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductWriteResponseDto;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.listEntity.DailyResultSet;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;
import com.travelproject.travelproject.repository.DailyTravelDateRepository;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.service.TourCourseService;

@Service
public class TourCourseServiceImplement implements TourCourseService {

    private TouristProductRepository touristProductRepository;
    private DailyTravelDateRepository dailyTravelDateRepository;

    @Autowired
    public TourCourseServiceImplement(TouristProductRepository touristProductRepository, DailyTravelDateRepository dailyTravelDateRepository) {
        this.touristProductRepository=touristProductRepository;
        this.dailyTravelDateRepository=dailyTravelDateRepository;
    }

    //! 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTouristProductListResponseDto> getTourCourseList() {
        GetTouristProductListResponseDto body = null;
        
        try {
            List<ProductResultSet> productEntities = touristProductRepository.getTourCourseList();
            body = new GetTouristProductListResponseDto(productEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }

    //! 상품 상세 조회
    @Override
    public ResponseEntity<? super GetTouristProductResponseDto> getTourCourse(Integer productBoardNumber) {
        GetTouristProductResponseDto body = null;

        try {
            //# 존재하지 않는 상품 번호
            TouristProductEntity touristProductEntity = touristProductRepository.findByProductNumber(productBoardNumber);
            if (touristProductEntity == null) return ResponseMessage.NOT_EXIST_PRODUCT_BOARD_NUMBER;

            List<DailyResultSet> dailyResultSet = dailyTravelDateRepository.findByProductNumber(productBoardNumber);
            ProductResultSet productResultSet = touristProductRepository.getLikeyCount();
            
            body = new GetTouristProductResponseDto(touristProductEntity, productResultSet, dailyResultSet);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }

    //! 지역, 이름 기준 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTouristProductWriteResponseDto> getTourCourseWriteList(String writeRegion, String writeTouristSpotName) {
        GetTouristProductWriteResponseDto body = null;

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }
}
