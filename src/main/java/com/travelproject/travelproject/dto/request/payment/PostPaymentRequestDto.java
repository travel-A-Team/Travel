package com.travelproject.travelproject.dto.request.payment;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostPaymentRequestDto {
    @NotBlank
    private String transactionId;
    @NotBlank
    private String paymentOrderNumber;
    @NotNull
    private Integer paymentAmount;
    @NotNull
    private Integer taxFreeAmount;
    @NotBlank
    private String paymentProductName;
    @NotBlank
    private String paymentDatetime;
}

