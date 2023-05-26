package com.travelproject.travelproject.dto.response.payment;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRefundResponseDto  extends ResponseDto{
    private Integer cancelAmount;
    private Integer cancelTaxFreeAmount;

    public GetRefundResponseDto(Integer cancelAmount, Integer cancelTaxFreeAmount) {
        super("SU", "Success");
        this.cancelAmount = cancelAmount;
        this.cancelTaxFreeAmount = cancelTaxFreeAmount;
    }
}
