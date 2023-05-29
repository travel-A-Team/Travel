package com.travelproject.travelproject.dto.response.tourRecommend;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;
import com.travelproject.travelproject.entity.resultSet.RegionResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetRecommendTourWriteResponseDto extends ResponseDto {
    private List<RegionList> region;
    private List<RecommendTourSpotList> recommendTourSpot;

    public GetRecommendTourWriteResponseDto(
        List<RegionResultSet> regionResultSet,
        List<RecommendResultSet> recommendResultSet) {
        super("SU", "SUCCESS");

        List<RegionList> region = new ArrayList<>();
        List<RecommendTourSpotList> recommendTourSpot = new ArrayList<>();

        for (RegionResultSet result : regionResultSet) {
            RegionList regionList = new RegionList(result);
            region.add(regionList);
        }
        this.region=region;

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
class RegionList {
    private String regionName;

    public RegionList(RegionResultSet resultSet) {
        this.regionName=resultSet.getRegionName();
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
