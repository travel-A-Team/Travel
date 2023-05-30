package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourListResponseDto;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;
import com.travelproject.travelproject.repository.RecommendationTouristSpotRepositroy;
import com.travelproject.travelproject.service.TourRecommendService;

@Service
public class TourRecommendServiceImplement implements TourRecommendService {

    private RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy;
    
    @Autowired
    public TourRecommendServiceImplement(RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy) {
        this.recommendationTouristSpotRepositroy=recommendationTouristSpotRepositroy;
    }

    //! 추천 여행지 목록 조회
    @Override
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendList() {
        GetRecommendTourListResponseDto body = null;

        try {
            List<RecommendationTouristSpotEntity> recommendResultSets = recommendationTouristSpotRepositroy.getRecommendationTouristSpotList();

            body = new GetRecommendTourListResponseDto(recommendResultSets);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //! 추천 여행지 상세 조회
    @Override
    public ResponseEntity<? super GetRecommendTourResponseDto> getRecommend(Integer touristSpotNumber) {
        GetRecommendTourResponseDto body = null;

        try {
            //# 요청 매개변수 검증 실패
            if (touristSpotNumber == null) return ResponseMessage.VAILDATION_FAILED;

            //# DB에서 받아온 엔터티 검증
            RecommendationTouristSpotEntity recommendEntity = recommendationTouristSpotRepositroy.findByRecommendationTouristSpotNumber(touristSpotNumber);
            if (recommendEntity == null) return ResponseMessage.NOT_EXIST_RECOMMENDATION_TOURIST_SPOT_NUMBER;
            
            body = new GetRecommendTourResponseDto(recommendEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //! 지역, 이름 기준 추천 여행지 조회
    @Override
    public ResponseEntity<? super GetRecommendTourListResponseDto> getRecommendWriteList(String writeRegion, String writeRecommendSpotName) {
        GetRecommendTourListResponseDto body = null;

        try {
            //# 요청 매개변수 검증 실패
            if (writeRegion == null || writeRecommendSpotName == null) return ResponseMessage.VAILDATION_FAILED;

            List<RecommendationTouristSpotEntity> recommendResultSets = recommendationTouristSpotRepositroy.findByRegionAndTitleContains(writeRegion, writeRecommendSpotName);

            body = new GetRecommendTourListResponseDto(recommendResultSets);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
