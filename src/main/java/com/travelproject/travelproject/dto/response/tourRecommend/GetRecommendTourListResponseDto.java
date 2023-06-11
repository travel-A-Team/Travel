package com.travelproject.travelproject.dto.response.tourRecommend;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetRecommendTourListResponseDto extends ResponseDto {
    private List<RecommendTourSpotList> recommendTourSpot;

    public GetRecommendTourListResponseDto(
        List<RecommendationTouristSpotEntity> recommendResultSet) {
        super("SU", "SUCCESS");

        List<RecommendTourSpotList> recommendTourSpot = new ArrayList<>();

        for (RecommendationTouristSpotEntity result : recommendResultSet) {
            RecommendTourSpotList recommendTourSpotList = new RecommendTourSpotList(result);
            recommendTourSpot.add(recommendTourSpotList);
        } 
        this.recommendTourSpot=recommendTourSpot;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class RecommendTourSpotList {
    private String recommendTouristspotImageUrl;
    private String touristspotTitle;
    private String content;

    public RecommendTourSpotList(RecommendationTouristSpotEntity resultSet) {
        this.recommendTouristspotImageUrl=resultSet.getImageUrl();
        this.touristspotTitle=resultSet.getTitle();
        this.content=resultSet.getContent();
    }
}
