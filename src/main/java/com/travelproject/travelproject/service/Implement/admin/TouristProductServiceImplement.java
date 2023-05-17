package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.RegionRepository;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.repository.TouristSpotRepository;
import com.travelproject.travelproject.service.admin.TouristProductService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class TouristProductServiceImplement implements TouristProductService {

    private final TouristProductRepository touristProductRepository;
    private final RegionRepository regionRepository;
    private final TouristSpotRepository touristSpotRepository;



    @Override
    public ResponseEntity<? super GetTouristProductFormResponseDto> getTouristProductForm(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetTouristProductFormResponseDto body = null;

        try {
            List<RegionEntity> regionEntities = regionRepository.findAll();
            List<TouristSpotEntity> touristSpotEntities = touristSpotRepository.getList();
            
            body = new GetTouristProductFormResponseDto(regionEntities, touristSpotEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }


        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
