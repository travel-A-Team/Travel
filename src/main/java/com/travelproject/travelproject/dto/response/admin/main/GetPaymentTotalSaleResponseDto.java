package com.travelproject.travelproject.dto.response.admin.main;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPaymentTotalSaleResponseDto extends ResponseDto {
   private Integer daySale;
   private Integer monthSale;
   private Integer yearSale; 

   public GetPaymentTotalSaleResponseDto(Integer daySale, Integer monthSale, Integer yearSale) {
      super("SU", "Success");
      
      this.daySale = daySale;
      this.monthSale = monthSale;
      this.yearSale = yearSale;
   }
}
