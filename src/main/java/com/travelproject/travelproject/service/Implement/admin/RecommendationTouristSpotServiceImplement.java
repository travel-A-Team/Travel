package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.FindRegionInAddress;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PatchRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot.GetRecommendationTouristSpotResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.RecommendationTouristSpotRepositroy;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.service.admin.RecommendationTouristSpotService;

@Service
public class RecommendationTouristSpotServiceImplement  implements RecommendationTouristSpotService{

    private RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy;
    private RegionRepository regionRepository;

    @Autowired
    public RecommendationTouristSpotServiceImplement(RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy, RegionRepository regionRepository) {
        this.recommendationTouristSpotRepositroy = recommendationTouristSpotRepositroy;
        this.regionRepository = regionRepository;
    }


    //* 추천 여행지 작성
    @Override
    public ResponseEntity<ResponseDto> postRecommendationTouristSpot(UserToken userToken, PostRecommendationTouristSpotRequestDto dto) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        String touristSpotAddress = dto.getRecommendTouristAddress();

        try {
            boolean existedTouristSpotAddress = recommendationTouristSpotRepositroy.existsByAddress(touristSpotAddress);
            if (existedTouristSpotAddress) return ResponseMessage.EXIST_RECOMMENDATION_TOURIST_SPOT_ADDRESS;

            String recommendTouristRegion = FindRegionInAddress.findRegionInAddress(touristSpotAddress);

            RegionEntity regionEntity = regionRepository.findByNameContains(recommendTouristRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            recommendTouristRegion = regionEntity.getName();

            RecommendationTouristSpotEntity recommendationTouristSpotEntity = new RecommendationTouristSpotEntity(recommendTouristRegion, dto);
            recommendationTouristSpotRepositroy.save(recommendationTouristSpotEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }


        return ResponseMessage.SUCCESS;
    }

    //* 특정 추천 여행지 조회
    @Override
    public ResponseEntity<? super GetRecommendationTouristSpotResponseDto> getRecommendationTouristSpot(UserToken userToken,Integer recommendTouristNumber) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if(recommendTouristNumber == null) return ResponseMessage.VAILDATION_FAILED;

        GetRecommendationTouristSpotResponseDto body = null;

        try {
            RecommendationTouristSpotEntity recommendationTouristSpotEntity = recommendationTouristSpotRepositroy.findByRecommendationTouristSpotNumber(recommendTouristNumber);
            if (recommendationTouristSpotEntity == null) return ResponseMessage.NOT_EXIST_RECOMMENDATION_TOURIST_SPOT_NUMBER;

            body = new GetRecommendationTouristSpotResponseDto(recommendationTouristSpotEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @Override
    public ResponseEntity<? super GetRecommendationTouristSpotListResponseDto> getRecommendationTouristSpotList(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetRecommendationTouristSpotListResponseDto body = null;

        try {
            List<RecommendationTouristSpotEntity> recommendationTouristSpotEntities = recommendationTouristSpotRepositroy.getRecommendationTouristSpotList();
            body = new GetRecommendationTouristSpotListResponseDto(recommendationTouristSpotEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }




    @Override
    public ResponseEntity<ResponseDto> patchRecommendationTouristSpot(UserToken userToken,PatchRecommendationTouristSpotRequestDto dto) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;


        int recommendTouristNumber = dto.getRecommendTouristNumber();
        String recommendTouristSpotTitle = dto.getRecommendTouristSpotTitle();
        String recommendTouristImageUrl = dto.getRecommendTouristImageUrl();
        String recommendTouristContent = dto.getRecommendTouristContent();
        String recommendTouristAddress = dto.getRecommendTouristAddress();
        String recommendTouristUseTime = dto.getRecommendTouristUseTime();
        String recommendTouristInformation = dto.getRecommendTouristInformation();

    
        try {
            RecommendationTouristSpotEntity recommendationTouristSpotEntity = recommendationTouristSpotRepositroy.findByRecommendationTouristSpotNumber(recommendTouristNumber);
            if (recommendationTouristSpotEntity == null) return ResponseMessage.NOT_EXIST_RECOMMENDATION_TOURIST_SPOT_NUMBER;
            String touristSpotAddress = recommendationTouristSpotEntity.getAddress();

            boolean equalRecommendTouristAddress = recommendTouristAddress.equals(touristSpotAddress);

            if (!equalRecommendTouristAddress) {
                boolean existedTouristSpotAddress = recommendationTouristSpotRepositroy.existsByAddress(recommendTouristAddress);
                if (existedTouristSpotAddress) return ResponseMessage.EXIST_RECOMMENDATION_TOURIST_SPOT_ADDRESS;
            }
            

            String recommendTouristRegion = FindRegionInAddress.findRegionInAddress(recommendTouristAddress);

            RegionEntity regionEntity = regionRepository.findByNameContains(recommendTouristRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            recommendTouristRegion = regionEntity.getName();


            recommendationTouristSpotEntity.setTitle(recommendTouristSpotTitle);
            recommendationTouristSpotEntity.setImageUrl(recommendTouristImageUrl);
            recommendationTouristSpotEntity.setContent(recommendTouristContent);
            recommendationTouristSpotEntity.setAddress(recommendTouristAddress);
            recommendationTouristSpotEntity.setRegion(recommendTouristRegion);
            recommendationTouristSpotEntity.setUseTime(recommendTouristUseTime);
            recommendationTouristSpotEntity.setInformation(recommendTouristInformation);

            recommendationTouristSpotRepositroy.save(recommendationTouristSpotEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }


        return ResponseMessage.SUCCESS;
    }


    @Override
    public ResponseEntity<ResponseDto> deleteRecommendationTouristSpot(UserToken userToken, Integer recommendTouristNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (recommendTouristNumber == null) return ResponseMessage.VAILDATION_FAILED;

        try {
            RecommendationTouristSpotEntity recommendationTouristSpotEntity = recommendationTouristSpotRepositroy.findByRecommendationTouristSpotNumber(recommendTouristNumber);
            if (recommendationTouristSpotEntity == null) return ResponseMessage.NOT_EXIST_RECOMMENDATION_TOURIST_SPOT_NUMBER;

            recommendationTouristSpotRepositroy.delete(recommendationTouristSpotEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
    
}
