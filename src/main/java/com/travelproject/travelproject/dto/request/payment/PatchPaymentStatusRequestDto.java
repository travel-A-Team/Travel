package com.travelproject.travelproject.dto.request.payment;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchPaymentStatusRequestDto {
    @NotBlank
    String transactionId;
    @NotBlank
    String status;
}
