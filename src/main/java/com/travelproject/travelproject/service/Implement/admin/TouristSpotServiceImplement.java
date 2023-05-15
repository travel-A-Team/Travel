package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PacthTouristSpotRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristSpot.GetTouristSpotResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.admin.TouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.repository.admin.TouristSpotRepository;
import com.travelproject.travelproject.service.admin.TouristSpotService;
import com.travelproject.travelproject.util.FindRegion;

@Service
public class TouristSpotServiceImplement  implements TouristSpotService{

    private TouristSpotRepository touristSpotRepository;
    private RegionRepository regionRepository;

    @Autowired
    public TouristSpotServiceImplement(TouristSpotRepository touristSpotRepository, RegionRepository regionRepository) {
        this.touristSpotRepository = touristSpotRepository;
        this.regionRepository = regionRepository;
    }

    //* 여행지 작성
    @Override
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, PostTouristSpotRequestDto dto) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;
        
        String writeTouristSpotAddress = dto.getWriteTouristSpotAddress();
    

        try {            
            //*  존재하는 주소
            boolean existedWriteTouristSpotAddress = touristSpotRepository.existsByWriteTouristSpotAddress(writeTouristSpotAddress);
            if (existedWriteTouristSpotAddress) return ResponseMessage.EXIST_WRITE_TOURIST_SPOT_ADDRESS;


            //* 입력 받은 주소에서 여행지 지역 이름 구하기
            int writeRegionIndex = writeTouristSpotAddress.indexOf(" ");
            String removeRegion = writeTouristSpotAddress.substring(writeRegionIndex);
            String writeRegion = writeTouristSpotAddress.replaceAll(removeRegion,"");

            writeRegion = FindRegion.findRegion(writeRegion);
            RegionEntity regionEntity = regionRepository.findByRegionNameContains(writeRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            writeRegion = regionEntity.getRegionName();

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

        if (writeTouristSpotNumber == null) return ResponseMessage.VAILDATION_FAILED;

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

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
        String role = userToken.getRole();
        if (!role.equals("admin")) return ResponseMessage.NO_PERMISSIONS;

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

            //TODO: 존재하는 여행지 주소 반환

            //* 입력 받은 주소에서 여행지 지역 이름 구하기
            int writeRegionIndex = writeTouristSpotAddress.indexOf(" ");
            System.out.println("writeRegionIndex"+ writeRegionIndex);
            String removeRegion = writeTouristSpotAddress.substring(writeRegionIndex);
            System.out.println("removeRegion"+ removeRegion);
            String writeRegion = writeTouristSpotAddress.replaceAll(removeRegion,"");
            System.out.println("writeRegion " + writeRegion);

            writeRegion = FindRegion.findRegion(writeRegion);
            RegionEntity regionEntity = regionRepository.findByRegionNameContains(writeRegion);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            writeRegion = regionEntity.getRegionName();

            touristSpotEntity.setWriteTouristSpotName(writeTouristSpotName);
            touristSpotEntity.setWriteImageUrl(writeImageUrl);
            touristSpotEntity.setWriteTouristSpotAddress(writeTouristSpotAddress);
            touristSpotEntity.setWriteRegion(writeRegion);

            
            touristSpotRepository.save(touristSpotEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    
    




}






