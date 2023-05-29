package com.travelproject.travelproject.dto.response.mainPage;

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
public class GetMainPageRecommendDto extends ResponseDto {
    List<RecommendTourspotTopList> recommendTopList;

    public GetMainPageRecommendDto(List<RecommendResultSet> recommendResultSet) {
        super("SU", "SUCCESS");

        List<RecommendTourspotTopList> recommendTopList = new ArrayList<>();

        for (RecommendResultSet result : recommendResultSet) {
            RecommendTourspotTopList recommendTourspotTopList = new RecommendTourspotTopList(result);
            recommendTopList.add(recommendTourspotTopList);
        }
        this.recommendTopList=recommendTopList;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class RecommendTourspotTopList {
    private String touristSpotTitle;
    private String recommendTouristSpotImageUrl;
    private String content;

    public RecommendTourspotTopList(RecommendResultSet recommendResultSet) {
        this.touristSpotTitle=recommendResultSet.getTouristSpotTitle();
        this.recommendTouristSpotImageUrl=recommendResultSet.getRecommendTouristSpotImageUrl();
        this.content=recommendResultSet.getContent();
    }
}
