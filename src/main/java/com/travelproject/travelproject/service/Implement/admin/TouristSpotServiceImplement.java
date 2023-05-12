package com.travelproject.travelproject.service.Implement.admin;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.adminTouristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.admin.TouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.repository.TouristSpotRepository;
import com.travelproject.travelproject.service.admin.TouristSpotService;
import com.travelproject.travelproject.util.FindRegion;

import lombok.Data;

@Service
public class TouristSpotServiceImplement  implements TouristSpotService{

    private TouristSpotRepository touristSpotRepository;
    private RegionRepository regionRepository;

    @Autowired
    public TouristSpotServiceImplement(TouristSpotRepository touristSpotRepository, RegionRepository regionRepository) {
        this.touristSpotRepository = touristSpotRepository;
        this.regionRepository = regionRepository;
    }


    @Override
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, PostTouristSpotRequestDto dto) {

        String role = userToken.getRole();
        if (!role.equals("admin")) return ResponseMessage.NO_PERMISSIONS;
        
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

    
    











}






