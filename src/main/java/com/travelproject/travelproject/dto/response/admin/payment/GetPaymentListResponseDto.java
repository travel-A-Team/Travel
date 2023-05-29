package com.travelproject.travelproject.dto.response.admin.payment;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPaymentListResponseDto extends ResponseDto {
    private List<Payment> paymentList;

    public GetPaymentListResponseDto(List<PaymentEntity> paymentEntities) {

        super("SU", "Success");

        List<Payment> paymentList = new ArrayList<>();

        for(PaymentEntity paymentEntity: paymentEntities) {
            Payment payment = new Payment(paymentEntity);
            paymentList.add(payment);
        }

        this.paymentList = paymentList;
    }
}

@Data
@NoArgsConstructor
class Payment {
    private String paymentProductName;
    private int paymentAmount;
    private String paymentStatus;
    private String paymentDatetime;
    private String paymentUserName;
    private String paymentUserEmail;
    private String paymentUserPhoneNumber;

    public Payment(PaymentEntity paymentEntity) {
        this.paymentProductName = paymentEntity.getProductName();
        this.paymentAmount = paymentEntity.getAmount();
        this.paymentStatus = paymentEntity.getStatus();
        this.paymentDatetime = paymentEntity.getDatetime();
        this.paymentUserName = paymentEntity.getUserName();
        this.paymentUserEmail = paymentEntity.getUserEmail();
        this.paymentUserPhoneNumber = paymentEntity.getUserPhoneNumber();
    }
}
