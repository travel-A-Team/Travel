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
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.TouristSpotRepository;
import com.travelproject.travelproject.service.admin.TouristSpotService;

import lombok.Data;

@Service
public class TouristSpotServiceImplement  implements TouristSpotService{

    private TouristSpotRepository touristSpotRepository;


    @Autowired
    public TouristSpotServiceImplement(TouristSpotRepository touristSpotRepository) {
        this.touristSpotRepository = touristSpotRepository;
    }


    @Override
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, MultipartFile touristSpotImageFile, PostTouristSpotRequestDto dto) {

        String role = userToken.getRole();
        if (!role.equals("admin")) return ResponseMessage.NO_PERMISSIONS;
        
        String writeTouristSpotAddress = dto.getWriteTouristSpotAddress();


        try {            
            //TODO: 존재하는 주소
            boolean existedWriteTouristSpotAddress = touristSpotRepository.existsByWriteTouristSpotAddress(writeTouristSpotAddress);
            if (!existedWriteTouristSpotAddress) return ResponseMessage.EXIST_WRITE_TOURIST_SPOT_ADDRESS;
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    
    











}






