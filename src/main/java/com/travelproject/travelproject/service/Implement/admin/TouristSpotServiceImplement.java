package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.FindRegionInAddress;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PacthTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.DailyTravelDateRepository;
import com.travelproject.travelproject.repository.PlannerDailyTravelDateRepository;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.repository.TouristSpotRepository;
import com.travelproject.travelproject.service.admin.TouristSpotService;

@Service
public class TouristSpotServiceImplement  implements TouristSpotService{

    private TouristSpotRepository touristSpotRepository;
    private RegionRepository regionRepository;
    private DailyTravelDateRepository dailyTravelDateRepository;
    private PlannerDailyTravelDateRepository plannerDailyTravelDateRepository;

    @Autowired
    public TouristSpotServiceImplement(TouristSpotRepository touristSpotRepository, RegionRepository regionRepository, DailyTravelDateRepository dailyTravelDateRepository, PlannerDailyTravelDateRepository plannerDailyTravelDateRepository) {
        this.touristSpotRepository = touristSpotRepository;
        this.regionRepository = regionRepository;
        this.dailyTravelDateRepository = dailyTravelDateRepository;
        this.plannerDailyTravelDateRepository = plannerDailyTravelDateRepository;
    }

    //* 여행지 작성
    @Override
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, PostTouristSpotRequestDto dto) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;
        
        String writeTouristSpotAddress = dto.getWriteTouristSpotAddress();
    

        try {            
            //*  존재하는 주소
            boolean existedWriteTouristSpotAddress = touristSpotRepository.existsByTouristSpotAddress(writeTouristSpotAddress);
            if (existedWriteTouristSpotAddress) return ResponseMessage.EXIST_WRITE_TOURIST_SPOT_ADDRESS;


            //* 입력 받은 주소에서 여행지 지역 이름 구하기
            String writeRegion = FindRegionInAddress.findRegionInAddress(writeTouristSpotAddress); 

            RegionEntity regionEntity = regionRepository.findByNameContains(writeRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            writeRegion = regionEntity.getName();

            //* 데이터베이스에 저장하기
            TouristSpotEntity touristSpotEntity = new TouristSpotEntity(writeRegion, dto);
            touristSpotRepository.save(touristSpotEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }


    //*  특정 여행지  조회
    @Override
    public ResponseEntity<? super GetTouristSpotResponseDto> getTouristSpot(UserToken userToken, Integer writeTouristSpotNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (writeTouristSpotNumber == null) return ResponseMessage.VAILDATION_FAILED;

        GetTouristSpotResponseDto body = null;

        try {
            //* 존재하지 않는 여행지 게시물 번호
            TouristSpotEntity touristSpotEntity = touristSpotRepository.findByTouristSpotNumber(writeTouristSpotNumber);
            if (touristSpotEntity == null) return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;
            
            body = new GetTouristSpotResponseDto(touristSpotEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



    //* 여행지 목록 조회
    @Override
    public ResponseEntity<? super GetTouristSpotListResponseDto> getTouristSpotList(UserToken userToken) {
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetTouristSpotListResponseDto body = null;

        try {
            
            List<TouristSpotEntity> touristSpotEntities = touristSpotRepository.getList();
            body = new GetTouristSpotListResponseDto(touristSpotEntities);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //*  특정 여행지  수정
    @Override
    public ResponseEntity<ResponseDto> pacthTouristSpot(UserToken userToken, PacthTouristSpotRequestDto dto) {
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        int writeTouristSpotNumber = dto.getWriteTouristSpotNumber();
        String writeTouristSpotName = dto.getWriteTouristSpotName();
        String writeImageUrl = dto.getWriteImageUrl();
        String writeTouristSpotAddress = dto.getWriteTouristSpotAddress();
        try {
            //* 존재하지 않는 여행지 게시물 번호
            TouristSpotEntity touristSpotEntity = touristSpotRepository.findByTouristSpotNumber(writeTouristSpotNumber);
            if (touristSpotEntity == null) return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;

            //* 존재하는 여행지 주소 반환
            
            String touristSpotAddress = touristSpotEntity.getTouristSpotAddress();
            boolean equalWriteTouristSpotAddress = writeTouristSpotAddress.equals(touristSpotAddress);

            if(!equalWriteTouristSpotAddress) {
                boolean existedWriteTouristSpotAddress = touristSpotRepository.existsByTouristSpotAddress(writeTouristSpotAddress);
                if (existedWriteTouristSpotAddress) return ResponseMessage.EXIST_WRITE_TOURIST_SPOT_ADDRESS;
            }



            //* 입력 받은 주소에서 여행지 지역 이름 구하기
            String writeRegion = FindRegionInAddress.findRegionInAddress(writeTouristSpotAddress); 
            
            RegionEntity regionEntity = regionRepository.findByNameContains(writeRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            writeRegion = regionEntity.getName();

            touristSpotEntity.setTouristSpotName(writeTouristSpotName);
            touristSpotEntity.setImageUrl(writeImageUrl);
            touristSpotEntity.setTouristSpotAddress(writeTouristSpotAddress);
            touristSpotEntity.setRegion(writeRegion);

            
            touristSpotRepository.save(touristSpotEntity);

            boolean existedDailyTouristSpotNumber = dailyTravelDateRepository.existsByTouristSpotNumber(writeTouristSpotNumber);
            if (existedDailyTouristSpotNumber) {
                dailyTravelDateRepository.updateTouristSpot(writeImageUrl, writeTouristSpotName, writeTouristSpotAddress, writeTouristSpotNumber);
            }

            boolean existedPlannerTouristSpotNumber = plannerDailyTravelDateRepository.existsByTouristSpotNumber(writeTouristSpotNumber);
            if (existedPlannerTouristSpotNumber) {
                plannerDailyTravelDateRepository.updateTouristSpot(writeImageUrl, writeTouristSpotName, writeTouristSpotAddress, writeTouristSpotNumber);
            }
 

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }


    //* 특정 여행지 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteTouristSpot(UserToken userToken, Integer writeTouristSpotNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (writeTouristSpotNumber == null) return ResponseMessage.VAILDATION_FAILED;

        try {
            TouristSpotEntity touristSpotEntity = touristSpotRepository.findByTouristSpotNumber(writeTouristSpotNumber);
            if (touristSpotEntity == null) return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;

            touristSpotRepository.delete(touristSpotEntity);
        
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;

    }



}






