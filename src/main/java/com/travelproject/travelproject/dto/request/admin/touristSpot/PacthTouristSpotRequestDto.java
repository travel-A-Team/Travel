package com.travelproject.travelproject.dto.request.admin.touristSpot;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacthTouristSpotRequestDto {
    @NotNull
    private Integer writeTouristSpotNumber;
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private String writeImageUrl;
    @NotBlank
    private String writeTouristSpotAddress;
}
