package com.travelproject.travelproject.service.Implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.questionBoard.PostProductLikeyRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductResponseDto;
import com.travelproject.travelproject.dto.response.touristProduct.GetTouristProductWriteResponseDto;
import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.resultSet.DailyResultSet;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.DailyTravelDateRepository;
import com.travelproject.travelproject.repository.LikeyRepository;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.TourCourseService;

@Service
public class TourCourseServiceImplement implements TourCourseService {

    private TouristProductRepository touristProductRepository;
    private DailyTravelDateRepository dailyTravelDateRepository;
    private LikeyRepository likeyRepository;
    private UserRepository userRepository;

    @Autowired
    public TourCourseServiceImplement(
            TouristProductRepository touristProductRepository,
            DailyTravelDateRepository dailyTravelDateRepository,
            LikeyRepository likeyRepository,
            UserRepository userRepository) {
        this.touristProductRepository=touristProductRepository;
        this.dailyTravelDateRepository=dailyTravelDateRepository;
        this.likeyRepository=likeyRepository;
        this.userRepository=userRepository;
    }

    //! 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTouristProductListResponseDto> getTourCourseList() {
        GetTouristProductListResponseDto body = null;
        
        try {
            List<ProductResultSet> productEntities = touristProductRepository.getTourCourseList();
            body = new GetTouristProductListResponseDto(productEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }

    //! 상품 상세 조회
    @Override
    public ResponseEntity<? super GetTouristProductResponseDto> getTourCourse(Integer productBoardNumber) {
        GetTouristProductResponseDto body = null;

        try {
            //# 존재하지 않는 상품 번호
            TouristProductEntity touristProductEntity = touristProductRepository.findByProductNumber(productBoardNumber);
            if (touristProductEntity == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            List<DailyResultSet> dailyResultSet = dailyTravelDateRepository.getDailyTravelDateList(productBoardNumber);
            ProductResultSet productResultSet = likeyRepository.getLikeyCount();
            
            body = new GetTouristProductResponseDto(touristProductEntity, productResultSet, dailyResultSet);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }

    //! 이름 기준 상품 목록 조회
    @Override
    public ResponseEntity<? super GetTouristProductWriteResponseDto> getTourCourseWriteList(String writeTouristProductName) {
        GetTouristProductWriteResponseDto body = null;

        try {
            //# 요청 매개변수 검증 실패
            if (writeTouristProductName == null) return ResponseMessage.VAILDATION_FAILED;

            //# 유사한 여행 상품 이름 있는지 확인
            List<ProductResultSet> productResultSet = touristProductRepository.getTourCourseList();
            List<ProductResultSet> searchProductList = new ArrayList<>();

            for (int count = 0; count < productResultSet.size(); count++) {
                if (productResultSet.get(count).getProductTitle().contains(writeTouristProductName)) {
                    searchProductList.add(productResultSet.get(count));
                }
            }

            body = new GetTouristProductWriteResponseDto(searchProductList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body); 
    }

    //! 좋아요 누르기
    @Override
    public ResponseEntity<ResponseDto> postTourCourseLikey(UserToken userToken, PostProductLikeyRequestDto dto) {

        try{
            // # 토큰 검증
            if (userToken == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            String productLikeyEmail = userToken.getEmail();

            // # 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(productLikeyEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            LikeyEntity likeyEntity = new LikeyEntity(productLikeyEmail, dto);
            likeyRepository.save(likeyEntity);

        } catch (Exception exception) {
            //# 데이터베이스 오류 반환
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        // # 성공 반환
        return ResponseMessage.SUCCESS;
    }

    //! 좋아요 취소
    @Override
    public ResponseEntity<ResponseDto> deleteTourCourseLikey(UserToken userToken, Integer productBoardNumber) {
    
        try {

            //# 토큰 검증
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
            String productLikeyEmail = userToken.getEmail();

            //# 요청 매개변수 검증 실패
            if (productBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;
            
            //# 존재하지 않는 상품 번호
            LikeyEntity likeyEntity = likeyRepository.getLikey(productBoardNumber);
            if (likeyEntity == null) return ResponseMessage.NOT_EXIST_TOURIST_PRODUCT_NUMBER;

            //# 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(productLikeyEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            //# 권한 없음
            boolean equalWriter = likeyEntity.getUserEmail().equals(productLikeyEmail);
            if (!equalWriter) return ResponseMessage.NO_PERMISSIONS;

            likeyRepository.delete(likeyEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
}
