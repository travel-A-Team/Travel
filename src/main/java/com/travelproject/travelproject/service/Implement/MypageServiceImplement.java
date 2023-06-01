package com.travelproject.travelproject.service.Implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.myPage.GetPaymentListResponseDto;
import com.travelproject.travelproject.dto.response.myPage.GetProductLikeResponseDto;
import com.travelproject.travelproject.dto.response.myPage.GetUserProfileResponseDto;
import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.PaymentEntity;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.LikeyRepository;
import com.travelproject.travelproject.repository.PaymentRepository;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.MyPageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MypageServiceImplement implements MyPageService {

    private final TouristProductRepository touristProductRepository;
    private final LikeyRepository likeyRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(UserToken userToken) {

        if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

        GetPaymentListResponseDto body = null;

        String userEmail = userToken.getEmail();
        try {

            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            List<PaymentEntity> paymentEntities = paymentRepository.getPaymentList(userEmail);
            body = new GetPaymentListResponseDto(paymentEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }



        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    //* 유저 프로필 조회
    @Override
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(UserToken userToken) {
        if(userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
        String userEmail = userToken.getEmail();
        GetUserProfileResponseDto body = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(userEmail);
            if(userEntity == null) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            body = new GetUserProfileResponseDto(userEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    



    // * 찜목록
    @Override
    public ResponseEntity<? super GetProductLikeResponseDto> getLikeProduct(UserToken userToken) {
        if(userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
        String userEmail = userToken.getEmail();
        GetProductLikeResponseDto body = null;
        try {
            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if(!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            List<LikeyEntity> likeEntities = likeyRepository.findByUserEmail(userEmail);

            List<TouristProductEntity> touristProductEntities = new ArrayList<>();
            for(LikeyEntity likeyEntity: likeEntities) {

               int productNumber = likeyEntity.getProductNumber();
               TouristProductEntity touristProductEntity = touristProductRepository.getUserLikeProduct(productNumber);
               touristProductEntities.add(touristProductEntity);
            }

            body = new GetProductLikeResponseDto(touristProductEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
