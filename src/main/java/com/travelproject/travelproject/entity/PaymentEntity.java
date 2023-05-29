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
    private String orderNumber;
    private String userEmail;
    private String userPhoneNumber;
    private String userName;
    private String productName;
    private int amount;
    private int taxFreeAmount;
    private String status;
    private String datetime;

    public PaymentEntity(String paymentUserEmail, String paymentUserPhoneNumber, String  paymentUserName, PostPaymentRequestDto dto) {
        this.transactionId = dto.getTransactionId();
        this.orderNumber = dto.getPaymentOrderNumber();
        this.userEmail = paymentUserEmail;
        this.userPhoneNumber = paymentUserPhoneNumber;
        this.userName = paymentUserName;
        this.productName = dto.getPaymentProductName();
        this.amount = dto.getPaymentAmount();
        this.taxFreeAmount = dto.getTaxFreeAmount();
        this.status = "결제 완료";
        this.datetime = dto.getPaymentDatetime();
    }
}
