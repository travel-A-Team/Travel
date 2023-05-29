package com.travelproject.travelproject.dto.response.admin.main;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPaymentListResponseDto  extends ResponseDto{
    private List<PaymentSummary> paymentList;
    
    public GetPaymentListResponseDto(List<PaymentEntity> paymentEntities) {

        super("SU", "Success");

        List<PaymentSummary> paymentList = new ArrayList<>();

        for (PaymentEntity paymentEntity: paymentEntities) {
            PaymentSummary paymentSummary = new PaymentSummary(paymentEntity);
            paymentList.add(paymentSummary);
        }
        this.paymentList = paymentList;
    }
}

@Data
@NoArgsConstructor
class PaymentSummary {
    private String paymentUserName;
    private String paymentProductName;
    private int paymentAmount;
    private String paymentStatus;
    private String paymentDatetime;

    public PaymentSummary(PaymentEntity paymentEntity) {
        this.paymentUserName = paymentEntity.getUserName();
        this.paymentProductName = paymentEntity.getProductName();
        this.paymentAmount = paymentEntity.getAmount();
        this.paymentStatus = paymentEntity.getStatus();
        this.paymentDatetime = paymentEntity.getDatetime();
    }
}
