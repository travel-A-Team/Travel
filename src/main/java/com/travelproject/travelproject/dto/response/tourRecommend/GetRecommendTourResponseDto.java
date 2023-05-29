package com.travelproject.travelproject.dto.response.tourRecommend;


import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;

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

    public GetRecommendTourResponseDto(RecommendResultSet recommendResultSet) {
        super("SU", "SUCCESS");
        this.touristSpotNumber=recommendResultSet.getRecommendationTouristSpotNumber();
        this.touristSpotTitle=recommendResultSet.getTouristSpotTitle();
        this.recommendTouristSpotImageUrl=recommendResultSet.getRecommendTouristSpotImageUrl();
        this.content=recommendResultSet.getContent();
        this.touristSpotAddress=recommendResultSet.getTouristSpotAddress();
        this.touristSpotUseTime=recommendResultSet.getTouristSpotUseTime();
        this.touristSpotInformation=recommendResultSet.getTouristSpotInformation();

    }
}
