package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.TouristSpotEntity;

import lombok.Data;

@Data
public class GetTouristProductFormResponseDto extends ResponseDto{
    List<Region> regionList;
    List<TouristSpotDto> touristSpotList;

    public GetTouristProductFormResponseDto(List<RegionEntity> regionEntities, List<TouristSpotEntity> touristSpotEntities) {
        
        super("SU", "Success");

        List<Region> regionList = new ArrayList<Region>();
        List<TouristSpotDto> touristSpotList = new ArrayList<TouristSpotDto>();

        for (RegionEntity regionEntity: regionEntities) {
            Region region = new Region(regionEntity);
            regionList.add(region);
        }

        for (TouristSpotEntity touristSpotEntity: touristSpotEntities) {
            TouristSpotDto touristSpot = new TouristSpotDto(touristSpotEntity);
            touristSpotList.add(touristSpot);
        }

        this.regionList = regionList;
        this.touristSpotList = touristSpotList;

    }
}

@Data
class Region {
    private String regionName;

    public Region(RegionEntity regionEntity) {
        this.regionName = regionEntity.getName();
    }
}
