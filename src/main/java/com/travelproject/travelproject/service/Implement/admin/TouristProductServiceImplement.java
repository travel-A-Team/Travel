package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductDaliyTravelDateRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.entity.DailyTravelDateEntity;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.DailyTravelDateRepository;
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
    private final DailyTravelDateRepository dailyTravelDateRepository;



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



    @Override
    public ResponseEntity<ResponseDto> postTouristProduct(UserToken userToken, PostTouristProductRequestDto dto) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        List<PostTouristProductDaliyTravelDateRequestDto> daliyTravelDateDtoList = dto.getDaliyTravelDateList();
        StringBuffer stringBuffer = new StringBuffer();

        for (PostTouristProductDaliyTravelDateRequestDto daliTravelDate: daliyTravelDateDtoList) {
            String touristSpotName = daliTravelDate.getWriteTouristSpotName();
            stringBuffer.append(" → " + touristSpotName);
        }

        int firstArrowIndex = 3;
        String productTourRoute = stringBuffer.substring(firstArrowIndex);

        String productImageUrl = daliyTravelDateDtoList.get(0).getWriteImageUrl();
        

        try {

            TouristProductEntity touristProductEntity = new TouristProductEntity(productTourRoute, productImageUrl, dto);
            touristProductRepository.save(touristProductEntity);
    
            int productNumber = touristProductEntity.getProductNumber();
    
    
            for (PostTouristProductDaliyTravelDateRequestDto daliTravelDateDto: daliyTravelDateDtoList) {
                DailyTravelDateEntity dailyTravelDateEntity = new DailyTravelDateEntity(productNumber, daliTravelDateDto);
                dailyTravelDateRepository.save(dailyTravelDateEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
    
}
