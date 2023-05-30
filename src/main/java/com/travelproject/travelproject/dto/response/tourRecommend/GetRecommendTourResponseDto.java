package com.travelproject.travelproject.dto.response.tourRecommend;


import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRecommendTourResponseDto extends ResponseDto {
    private int touristSpotNumber;
    private String touristSpotTitle;
    private String recommendTouristSpotImageUrl;
    private String content;
    private String touristSpotAddress;
    private String touristSpotUseTime;
    private String touristSpotInformation;

    public GetRecommendTourResponseDto(RecommendationTouristSpotEntity recommendResultSet) {
        super("SU", "SUCCESS");
        this.touristSpotNumber=recommendResultSet.getRecommendationTouristSpotNumber();
        this.touristSpotTitle=recommendResultSet.getTitle();
        this.recommendTouristSpotImageUrl=recommendResultSet.getImageUrl();
        this.content=recommendResultSet.getContent();
        this.touristSpotAddress=recommendResultSet.getAddress();
        this.touristSpotUseTime=recommendResultSet.getUseTime();
        this.touristSpotInformation=recommendResultSet.getInformation();

    }
}
