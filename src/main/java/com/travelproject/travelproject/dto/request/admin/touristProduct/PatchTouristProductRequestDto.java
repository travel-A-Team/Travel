package com.travelproject.travelproject.dto.request.admin.touristProduct;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PatchTouristProductRequestDto {
    @NotNull
    private Integer productNumber;
    @NotBlank
    private String productTitle;
    @NotBlank
    private String productTotalSchedule;
    @NotNull
    private Integer productMoney;
    @NotNull
    private List<PatchTouristProductDaliyTravelDateRequestDto> dailyTravelDateList;
    private List<DeleteDailyTravelNumber> deleteDailyTravelNumberList;
}

