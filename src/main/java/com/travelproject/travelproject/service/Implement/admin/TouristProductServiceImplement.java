package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.touristProduct.DeleteDailyTravelNumber;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductDaliyTravelDateRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductDaliyTravelDateRequestDto;
import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchRegionAndTouristSpotNameResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchRegionResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetSearchTouristSpotNameResultResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductFormResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.entity.DailyTravelDateEntity;
import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.entity.resultSet.DailyResultSet;
import com.travelproject.travelproject.entity.resultSet.TouristProductListResultSet;
import com.travelproject.travelproject.entity.resultSet.TouristProductResultSet;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.DailyTravelDateRepository;
import com.travelproject.travelproject.repository.LikeyRepository;
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
    private final LikeyRepository likeyRepository;



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
    public ResponseEntity<? super GetSearchRegionResultResponseDto> getSearchRegionResult(UserToken userToken, String region) {
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if(region == null) return ResponseMessage.VAILDATION_FAILED;

        GetSearchRegionResultResponseDto body = null;

        try {
            RegionEntity regionEntity = regionRepository.findByName(region);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            List<TouristSpotEntity> touristSpotEntities = touristSpotRepository.findByRegionContains(region);

            body = new GetSearchRegionResultResponseDto(touristSpotEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



    @Override
    public ResponseEntity<? super GetSearchTouristSpotNameResultResponseDto> getSearchTouristSpotNameResult(UserToken userToken, String touristSpotName) {
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (touristSpotName == null) return ResponseMessage.VAILDATION_FAILED;

        GetSearchTouristSpotNameResultResponseDto body = null;
        try {
            List<TouristSpotEntity> touristSpotEntities = touristSpotRepository.findByTouristSpotNameContains(touristSpotName);

            body = new GetSearchTouristSpotNameResultResponseDto(touristSpotEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



    @Override
    public ResponseEntity<? super GetSearchRegionAndTouristSpotNameResultResponseDto> getSearchRegionAndTouristSpotNameResult(
            UserToken userToken, String region, String touristSpotName) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (region == null || touristSpotName == null) return ResponseMessage.VAILDATION_FAILED;

        GetSearchRegionAndTouristSpotNameResultResponseDto body = null;
        try {
            RegionEntity regionEntity = regionRepository.findByName(region);
            if (regionEntity == null) return ResponseMessage.NOT_EXIST_REGION_NAME;

            List<TouristSpotEntity> touristSpotEntities = touristSpotRepository.findByRegionAndTouristSpotNameContains(region, touristSpotName);

            body = new GetSearchRegionAndTouristSpotNameResultResponseDto(touristSpotEntities);
        
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

        for (PostTouristProductDaliyTravelDateRequestDto daliyTravelDate: daliyTravelDateDtoList) {
            String touristSpotName = daliyTravelDate.getWriteTouristSpotName();
            stringBuffer.append(" → " + touristSpotName);
        }

        int firstArrowIndex = 3;
        String productTourRoute = stringBuffer.substring(firstArrowIndex);

        String productImageUrl = daliyTravelDateDtoList.get(0).getWriteImageUrl();
        

        try {

            for (PostTouristProductDaliyTravelDateRequestDto daliyTravelDateDto: daliyTravelDateDtoList) {
                int touristSpotNumber = daliyTravelDateDto.getTouristSpotNumber();
    
                boolean existedTouristSpotNumber = touristSpotRepository.existsByTouristSpotNumber(touristSpotNumber);
                if (!existedTouristSpotNumber) return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;
            }

            TouristProductEntity touristProductEntity = new TouristProductEntity(productTourRoute, productImageUrl, dto);
            touristProductRepository.save(touristProductEntity);
    
            int productNumber = touristProductEntity.getProductNumber();
    
    
            for (PostTouristProductDaliyTravelDateRequestDto daliyTravelDateDto: daliyTravelDateDtoList) {
                DailyTravelDateEntity dailyTravelDateEntity = new DailyTravelDateEntity(productNumber, daliyTravelDateDto);
                dailyTravelDateRepository.save(dailyTravelDateEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }



    @Override
    public ResponseEntity<? super GetTouristProductResponseDto> getTouristProduct(UserToken userToken, Integer productNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (productNumber == null) return ResponseMessage.VAILDATION_FAILED;

        GetTouristProductResponseDto body = null;

        try {
            TouristProductResultSet touristProductResultSet = touristProductRepository.getTouristProduct(productNumber);
            if (touristProductResultSet == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            List<DailyResultSet> dailyResultSetList = dailyTravelDateRepository.getDailyTravelDateList(productNumber);

            body = new GetTouristProductResponseDto(touristProductResultSet, dailyResultSetList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



    @Override
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetTouristProductListResponseDto body = null;

        try {

            List<TouristProductListResultSet> touristProductListResultSetList = touristProductRepository.getTouristProductList();
            body = new GetTouristProductListResponseDto(touristProductListResultSetList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }



    @Override
    public ResponseEntity<ResponseDto> patchTouristProduct(UserToken userToken, PatchTouristProductRequestDto dto) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;
       
        int productNumber = dto.getProductNumber();
        String productTitle = dto.getProductTitle();
        String productTotalSchedule = dto.getProductTotalSchedule();
        int productMoney = dto.getProductMoney();
        List<PatchTouristProductDaliyTravelDateRequestDto> daliyTravelDateDtoList = dto.getDailyTravelDateList();
        StringBuffer stringBuffer = new StringBuffer();
        String productImageUrl;
        

        
        for (PatchTouristProductDaliyTravelDateRequestDto daliyTravelDate: daliyTravelDateDtoList) {
            String touristSpotName = daliyTravelDate.getWriteTouristSpotName();
            stringBuffer.append(" → " + touristSpotName);

            int firstSequence = 1;
            int dailyTravelDateSequence =  daliyTravelDate.getWriteSequence();
            boolean firstdailyTravelDate = firstSequence == dailyTravelDateSequence;

            if (firstdailyTravelDate) {
                productImageUrl = daliyTravelDate.getWriteImageUrl();
            }
        }

        int firstArrowIndex = 3;
        String productTourRoute = stringBuffer.substring(firstArrowIndex);


        try {

            TouristProductEntity touristProductEntity = touristProductRepository.findByProductNumber(productNumber);
            if (touristProductEntity == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            for (PatchTouristProductDaliyTravelDateRequestDto daliyTravelDateDto: daliyTravelDateDtoList) {
                int touristSpotNumber = daliyTravelDateDto.getTouristSpotNumber();
    
                boolean existedTouristSpotNumber = touristSpotRepository.existsByTouristSpotNumber(touristSpotNumber);
                if (!existedTouristSpotNumber) return ResponseMessage.NOT_EXIST_WRITE_TOURIST_SPOT_NUMBER;
            }

            touristProductEntity.setTitle(productTitle);
            touristProductEntity.setTotalSchedule(productTotalSchedule);
            touristProductEntity.setMoney(productMoney);
            touristProductEntity.setTourRoute(productTourRoute);

            for (PatchTouristProductDaliyTravelDateRequestDto daliyTravelDate: daliyTravelDateDtoList) {
                Integer dailyTravelNumber = daliyTravelDate.getDailyTravelNumber();
                int touristSpotNumber = daliyTravelDate.getTouristSpotNumber();
                String dailyTravelDate = daliyTravelDate.getDailyTravelDate();
                int writeSequence = daliyTravelDate.getWriteSequence();
                String writeImageUrl = daliyTravelDate.getWriteImageUrl();
                String writeTouristSpotName = daliyTravelDate.getWriteTouristSpotName();
                String writeProductAddress = daliyTravelDate.getWriteProductAddress();

                if (dailyTravelNumber == null) {
                    DailyTravelDateEntity dailyTravelDateEntity = new DailyTravelDateEntity(productNumber, daliyTravelDate);
                    dailyTravelDateRepository.save(dailyTravelDateEntity);
                    continue;
                }

                DailyTravelDateEntity dailyTravelDateEntity = dailyTravelDateRepository.findByDailyTravelNumber(dailyTravelNumber);

                dailyTravelDateEntity.setTouristSpotNumber(touristSpotNumber);
                dailyTravelDateEntity.setTravelDate(dailyTravelDate);
                dailyTravelDateEntity.setSequence(writeSequence);
                dailyTravelDateEntity.setTouristSpotImageUrl(writeImageUrl);
                dailyTravelDateEntity.setTouristSpotName(writeTouristSpotName);
                dailyTravelDateEntity.setTouristSpotAddress(writeProductAddress);
                
                dailyTravelDateRepository.save(dailyTravelDateEntity);
            }

            if (dto.getDeleteDailyTravelNumberList() != null) {
                List<DeleteDailyTravelNumber> deleteDailyTravelNumberList = dto.getDeleteDailyTravelNumberList();

                for (DeleteDailyTravelNumber deleteDailyTravelNumber: deleteDailyTravelNumberList) {
                    Integer dailyTravelNumber = deleteDailyTravelNumber.getDailyTravelNumber();
                    if (dailyTravelNumber == null) break;
    
                    DailyTravelDateEntity dailyTravelDateEntity = dailyTravelDateRepository.findByDailyTravelNumber(dailyTravelNumber);
                    dailyTravelDateRepository.delete(dailyTravelDateEntity);
                }
            }
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }




        return ResponseMessage.SUCCESS;
    }



    @Override
    public ResponseEntity<ResponseDto> deleteTouristProduct(UserToken userToken, Integer productNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (productNumber == null) return ResponseMessage.VAILDATION_FAILED;

        try {
            TouristProductEntity touristProductEntity = touristProductRepository.findByProductNumber(productNumber);
            if (touristProductEntity == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            List<DailyTravelDateEntity> dailyTravelDateEntities = dailyTravelDateRepository.findByProductNumber(productNumber);
            List<LikeyEntity> likeyEntities =likeyRepository.findByProductNumber(productNumber);
            
            likeyRepository.deleteAll(likeyEntities);
            dailyTravelDateRepository.deleteAll(dailyTravelDateEntities);
            touristProductRepository.delete(touristProductEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        
        return ResponseMessage.SUCCESS;
    }



 
    
}
