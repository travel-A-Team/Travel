package com.travelproject.travelproject.dto.response.plannerBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.listEntity.PlannerWriteRegionResultSet;
import com.travelproject.travelproject.entity.listEntity.PlannerWriteSpotResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetPlannerWriteListResponseDto extends ResponseDto {
    private List<Region> regionList;

    private List<TouristSpot> touristSpotList;

    public GetPlannerWriteListResponseDto(List<PlannerWriteRegionResultSet> plannerWriteRegionResultSet,
            List<PlannerWriteSpotResultSet> plannerWriteSpotResultSet) {

        super("SU", "Success");

        List<Region> regionList = new ArrayList<>();
        List<TouristSpot> touristSpotList = new ArrayList<>();

        for (PlannerWriteRegionResultSet result : plannerWriteRegionResultSet) {
            Region region = new Region(result);
            regionList.add(region);

        }

        for (PlannerWriteSpotResultSet result : plannerWriteSpotResultSet) {
            TouristSpot touristSpot = new TouristSpot(result);
            touristSpotList.add(touristSpot);

        }
        this.regionList = regionList;
        this.touristSpotList = touristSpotList;
    }


}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Region {
    private String regionName;

    public Region(PlannerWriteRegionResultSet resultSet) {
        this.regionName = resultSet.getRegionName();
    }
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TouristSpot {
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writeTouristSpotAddress;
    private double latitude;
    private double longitude;

    public TouristSpot(PlannerWriteSpotResultSet resultSet) {
        this.writeImageUrl = resultSet.getWriteImageUrl();
        this.writeTouristSpotName = resultSet.getWriteTouristSpotName();
        this.writeTouristSpotAddress = resultSet.getWriteTouristSpotAddress();
        this.latitude = resultSet.getLatitude();
        this.longitude = resultSet.getLongitude();
    }
}
