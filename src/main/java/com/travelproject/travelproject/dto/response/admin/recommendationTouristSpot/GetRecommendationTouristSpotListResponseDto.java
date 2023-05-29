package com.travelproject.travelproject.dto.response.admin.recommendationTouristSpot;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRecommendationTouristSpotListResponseDto extends ResponseDto {
    List<RecommendationTouristSpotSummary> recommendTouristSpotList;

    public GetRecommendationTouristSpotListResponseDto(List<RecommendationTouristSpotEntity> recommendationTouristSpotEntities) {
        super("SU", "Success");

        List<RecommendationTouristSpotSummary> recommendTouristSpotList = new ArrayList<RecommendationTouristSpotSummary>();

        for (RecommendationTouristSpotEntity recommendationTouristSpotEntity: recommendationTouristSpotEntities) {

                RecommendationTouristSpotSummary recommendationTouristSpotSummary = new RecommendationTouristSpotSummary(recommendationTouristSpotEntity);

                recommendTouristSpotList.add(recommendationTouristSpotSummary);
        }

        this.recommendTouristSpotList = recommendTouristSpotList;

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class RecommendationTouristSpotSummary {
    private int recommendTouristNumber;
    private String recommendTouristSpotTitle;
    private String recommendTouristImageUrl;
    private String recommendTouristContent;
    private String recommendTouristWriteDate;

    public RecommendationTouristSpotSummary(RecommendationTouristSpotEntity recommendationTouristSpotEntity) {
        this.recommendTouristNumber = recommendationTouristSpotEntity.getRecommendationTouristSpotNumber();
        this.recommendTouristSpotTitle = recommendationTouristSpotEntity.getTitle();
        this.recommendTouristImageUrl = recommendationTouristSpotEntity.getImageUrl();
        this.recommendTouristContent = recommendationTouristSpotEntity.getContent();
        this.recommendTouristWriteDate = recommendationTouristSpotEntity.getWriteDate();
    }
}
