package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.payment.PostPaymentRequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "Payment")
@Table(name = "Payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentNumber;
    private String transactionId;
    private String paymentOrderNumber;
    private String paymentUserEmail;
    private String paymentUserPhoneNumber;
    private String paymentUserName;
    private String paymentProductName;
    private int paymentAmount;
    private int taxFreeAmount;
    private String paymentStatus;
    private String paymentDatetime;

    public PaymentEntity(String paymentUserEmail, String paymentUserPhoneNumber, String  paymentUserName, PostPaymentRequestDto dto) {
        this.transactionId = dto.getTransactionId();
        this.paymentOrderNumber = dto.getPaymentOrderNumber();
        this.paymentUserEmail = paymentUserEmail;
        this.paymentUserPhoneNumber = paymentUserPhoneNumber;
        this.paymentUserName = paymentUserName;
        this.paymentProductName = dto.getPaymentProductName();
        this.paymentAmount = dto.getPaymentAmount();
        this.taxFreeAmount = dto.getTaxFreeAmount();
        this.paymentStatus = "결제 완료";
        this.paymentDatetime = dto.getPaymentDatetime();
    }
}
