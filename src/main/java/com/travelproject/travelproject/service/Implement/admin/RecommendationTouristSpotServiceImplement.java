package com.travelproject.travelproject.service.Implement.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.FindRegionInAddress;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.tourRecommend.GetRecommendTourResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.RecommendationTouristSpotRepositroy;
import com.travelproject.travelproject.service.admin.RecommendationTouristSpotService;

@Service
public class RecommendationTouristSpotServiceImplement  implements RecommendationTouristSpotService{

    private RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy;

    @Autowired
    public RecommendationTouristSpotServiceImplement(RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy) {
        this.recommendationTouristSpotRepositroy = recommendationTouristSpotRepositroy;
    }


    //* 추천 여행지 작성
    @Override
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(UserToken userToken, PostRecommendationTouristSpotRequestDto dto) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        String touristSpotAddress = dto.getRecommendTouristAddress();

        try {
            boolean existedTouristSpotAddress = recommendationTouristSpotRepositroy.existsByTouristSpotAddress(touristSpotAddress);
            if (existedTouristSpotAddress) return ResponseMessage.EXIST_RECOMMENDATION_TOURIST_SPOT_ADDRESS;

            String recommendTouristRegion = FindRegionInAddress.findRegionInAddress(touristSpotAddress);

            RecommendationTouristSpotEntity recommendationTouristSpotEntity = new RecommendationTouristSpotEntity(recommendTouristRegion, dto);
            recommendationTouristSpotRepositroy.save(recommendationTouristSpotEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }


        return ResponseMessage.SUCCESS;
    }

    //* 특정 여행지 조회
    @Override
    public ResponseEntity<? super GetRecommendTourResponseDto> getRecommendationTouristSpot(UserToken userToken,Integer recommendTouristNumber) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if(recommendTouristNumber == null) return ResponseMessage.NOT_EXIST_RECOMMENDATION_TOURIST_SPOT_NUMBER;

        ResponseEntity<? super GetRecommendTourResponseDto> body = null;

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
