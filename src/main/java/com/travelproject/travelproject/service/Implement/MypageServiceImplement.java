package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.myPage.GetPaymentListResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.PaymentRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.MyPageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MypageServiceImplement implements MyPageService {

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
    
}
