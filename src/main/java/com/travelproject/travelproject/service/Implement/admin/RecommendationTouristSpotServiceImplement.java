package com.travelproject.travelproject.service.Implement.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
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
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }


        return ResponseMessage.SUCCESS;
    }
    
}
