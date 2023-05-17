package com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchRecommendationTouristSpotRequestDto {
    @NotNull
    private Integer recommendTouristNumber;
    @NotBlank
    private String recommendTouristSpotTitle;
    @NotBlank
    private String recommendTouristImageUrl;
    @NotBlank
    private String recommendTouristContent;
    @NotBlank
    private String recommendTouristAddress;
    @NotBlank
    private String recommendTouristUseTime;
    @NotBlank
    private String recommendTouristInformation;
}
