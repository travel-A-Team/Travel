package com.travelproject.travelproject.dto.response.myPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPaymentListResponseDto  extends ResponseDto{
    List<Payment> paymentList;

    public GetPaymentListResponseDto(List<PaymentEntity> paymentEntities) {

        super("SU", "Success");

        List<Payment> paymentList = new ArrayList<>();

        for (PaymentEntity paymentEntity: paymentEntities) {
            Payment payment = new Payment(paymentEntity);
            paymentList.add(payment);
        }

        this.paymentList = paymentList;
    }
}

@Data
@NoArgsConstructor
class Payment {
    private String transactionId;
    private String paymentProductName;
    private int paymentOrderNumber;
    private String paymentStatus;
    private int paymentAmount;
    private String paymentDatetime;

    public Payment(PaymentEntity paymentEntity) {
        String paymentOrderNumber = paymentEntity.getOrderNumber();
        this.transactionId = paymentEntity.getTransactionId();
        this.paymentProductName = paymentEntity.getUserName();
        this.paymentOrderNumber = Integer.parseInt(paymentOrderNumber);
        this.paymentStatus = paymentEntity.getStatus();
        this.paymentAmount = paymentEntity.getAmount();
        this.paymentDatetime = paymentEntity.getDatetime();
    }
}
