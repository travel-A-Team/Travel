package com.travelproject.travelproject.dto.request.admin.touristProduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PatchTouristProductDaliyTravelDateRequestDto {

    private Integer dailyTravelNumber;
    @NotNull
    private Integer touristSpotNumber;
    @NotBlank
    private String dailyTravelDate;
    @NotNull
    private Integer writeSequence;
    @NotBlank
    private String writeImageUrl;
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private String writeProductAddress;
}
