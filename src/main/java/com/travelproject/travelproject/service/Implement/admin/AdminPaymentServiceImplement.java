package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.response.admin.payment.GetPaymentListResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.PaymentRepository;
import com.travelproject.travelproject.service.admin.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminPaymentServiceImplement implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentLIst(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetPaymentListResponseDto body = null;

        try {
            
            List<PaymentEntity> paymentEntities = paymentRepository.getPaymentList();
            body = new GetPaymentListResponseDto(paymentEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }
    
}
