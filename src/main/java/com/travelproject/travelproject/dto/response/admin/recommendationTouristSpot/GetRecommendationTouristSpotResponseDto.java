package com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRecommendationTouristSpotResponseDto extends ResponseDto {
    private int recommendTouristNumber;
    private String recommendTouristSpotTitle;
    private String recommendTouristImageUrl;
    private String recommendTouristContent;
    private String recommendTouristAddress;
    private String recommendTouristUseTime;
    private String recommendTouristInformation;

    public GetRecommendationTouristSpotResponseDto(RecommendationTouristSpotEntity recommendationTouristSpotEntity) {
        super("SU", "Success");

        this.recommendTouristNumber = recommendationTouristSpotEntity.getTouristSpotNumber();
        this.recommendTouristSpotTitle = recommendationTouristSpotEntity.getTouristSpotTitle();
        this.recommendTouristImageUrl = recommendationTouristSpotEntity.getRecommendTouristSpotImageUrl();
        this.recommendTouristContent = recommendationTouristSpotEntity.getContent();
        this.recommendTouristAddress = recommendationTouristSpotEntity.getTouristSpotAddress();
        this.recommendTouristUseTime = recommendationTouristSpotEntity.getTouristSpotUseTime();
        this.recommendTouristInformation = recommendationTouristSpotEntity.getTouristSpotInformation();
    }
}
