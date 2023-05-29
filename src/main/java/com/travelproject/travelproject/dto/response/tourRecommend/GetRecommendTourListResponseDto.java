package com.travelproject.travelproject.dto.response.tourRecommend;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;

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
        List<RecommendResultSet> recommendResultSet) {
        super("SU", "SUCCESS");

        List<RecommendTourSpotList> recommendTourSpot = new ArrayList<>();

        for (RecommendResultSet result : recommendResultSet) {
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

    public RecommendTourSpotList(RecommendResultSet resultSet) {
        this.recommendTouristspotImageUrl=resultSet.getRecommendTouristSpotImageUrl();
        this.touristspotTitle=resultSet.getTouristSpotTitle();
        this.content=resultSet.getContent();
    }
}
