package com.travelproject.travelproject.service.Implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.payment.PostPaymentRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.payment.GetRefundResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.PaymentRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentServiceImplement  implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<ResponseDto> postPayment(UserToken userToken, PostPaymentRequestDto dto) {

        if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

        String userEmail = userToken.getEmail();

        try {

            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            UserEntity userEntity = userRepository.findByEmail(userEmail);
            String userNmae = userEntity.getName();
            String userPhoneNumber = userEntity.getPhoneNumber();

            PaymentEntity paymentEntity = new PaymentEntity(userEmail, userPhoneNumber, userNmae, dto);

            paymentRepository.save(paymentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    

    @Override
    public ResponseEntity<? super GetRefundResponseDto> getRefund(UserToken userToken, String transactionId) {

        if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

        GetRefundResponseDto body = null;

        try {

            PaymentEntity paymentEntity = paymentRepository.findByTransactionId(transactionId);
            if (paymentEntity == null) return ResponseMessage.NOT_EXIST_TRANSACTION_ID;

            Integer cancelAmount = paymentEntity.getPaymentAmount();
            Integer cancelTaxFreeAmount = paymentEntity.getTaxFreeAmount();

            body = new GetRefundResponseDto(cancelAmount, cancelTaxFreeAmount);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> patchRefund(UserToken userToken, String transactionId, String status) {

        if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

        String userEmail = userToken.getEmail();
        
        String cancelSuccess = "CANCEL_PAYMENT";

        if (!status.equals(cancelSuccess)) return ResponseMessage.PAYMENT_CANCEL_FAILD;

        try {

            PaymentEntity paymentEntity = paymentRepository.findByTransactionId(transactionId);
            if (paymentEntity == null) return ResponseMessage.NOT_EXIST_TRANSACTION_ID;

            String paymentUserEmail  = paymentEntity.getPaymentUserEmail();
            boolean equalsUserEmail = userEmail.equals(paymentUserEmail);
            if (!equalsUserEmail) return ResponseMessage.NO_PERMISSIONS;

            String paymentStatus = paymentEntity.getPaymentStatus();
            paymentStatus = "결제 취소";

            paymentEntity.setPaymentStatus(paymentStatus);
            paymentRepository.save(paymentEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
    
}
