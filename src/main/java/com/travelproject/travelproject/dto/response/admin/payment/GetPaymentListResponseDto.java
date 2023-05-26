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
        this.paymentProductName = paymentEntity.getPaymentProductName();
        this.paymentAmount = paymentEntity.getPaymentAmount();
        this.paymentStatus = paymentEntity.getPaymentStatus();
        this.paymentDatetime = paymentEntity.getPaymentDatetime();
        this.paymentUserName = paymentEntity.getPaymentUserName();
        this.paymentUserEmail = paymentEntity.getPaymentUserEmail();
        this.paymentUserPhoneNumber = paymentEntity.getPaymentUserPhoneNumber();
    }
}
