package com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRecommendationTouristSpotResponseDto extends ResponseDto {
    private String recommendTouristSpotTitle;
    private String recommendTouristImageUrl;
    private String content;
    private String recommendTouristAddress;
    private String recommendTouristUseTime;
    private String recommendTouristInformation;
}
