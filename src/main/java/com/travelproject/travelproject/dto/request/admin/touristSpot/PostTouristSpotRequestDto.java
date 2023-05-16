package com.travelproject.travelproject.dto.request.admin.touristSpot;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTouristSpotRequestDto {
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private String writeImageUrl;
    @NotBlank
    private String writeTouristSpotAddress;   
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
