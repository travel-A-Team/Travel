package com.travelproject.travelproject.dto.response.plannerBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;

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

    public GetPlannerWriteListResponseDto(List<RegionEntity> regionEntities,
            List<TouristSpotEntity> touristSpotEntities) {

        super("SU", "Success");

        List<Region> regionList = new ArrayList<>();
        List<TouristSpot> touristSpotList = new ArrayList<>();

        for (RegionEntity regionEntity : regionEntities) {
            Region region = new Region(regionEntity);
            regionList.add(region);

        }

        for (TouristSpotEntity touristSpotEntity : touristSpotEntities) {
            TouristSpot touristSpot = new TouristSpot(touristSpotEntity);
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

    public Region(RegionEntity regionEntity) {
        this.regionName = regionEntity.getName();
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

    public TouristSpot(TouristSpotEntity touristSpotEntity) {
        this.writeImageUrl = touristSpotEntity.getImageUrl();
        this.writeTouristSpotName = touristSpotEntity.getTouristSpotName();
        this.writeTouristSpotAddress = touristSpotEntity.getTouristSpotAddress();
        this.latitude = touristSpotEntity.getLatitude();
        this.longitude = touristSpotEntity.getLongitude();
    }
}
