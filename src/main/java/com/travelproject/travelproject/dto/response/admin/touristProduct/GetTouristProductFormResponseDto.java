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
    List<TouristSpot> touristSpotList;

    public GetTouristProductFormResponseDto(List<RegionEntity> regionEntities, List<TouristSpotEntity> touristSpotEntities) {
        
        super("SU", "Success");

        List<Region> regionList = new ArrayList<Region>();
        List<TouristSpot> touristSpotList = new ArrayList<TouristSpot>();

        for (RegionEntity regionEntity: regionEntities) {
            Region region = new Region(regionEntity);
            regionList.add(region);
        }

        for (TouristSpotEntity touristSpotEntity: touristSpotEntities) {
            TouristSpot touristSpot = new TouristSpot(touristSpotEntity);
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
        this.regionName = regionEntity.getRegionName();
    }
}

@Data
class TouristSpot {
    private int touristSpotNumber;
    private String  touristSpotImageUrl;
    private String  touristSpotName;
    private String  touristSpotAddress;
    private String  touristSpotRegion;

    public TouristSpot(TouristSpotEntity touristSpotEntity) {
        this.touristSpotNumber = touristSpotEntity.getTouristSpotNumber();
        this.touristSpotImageUrl = touristSpotEntity.getWriteImageUrl();
        this.touristSpotName = touristSpotEntity.getWriteTouristSpotName();
        this.touristSpotAddress = touristSpotEntity.getWriteTouristSpotAddress();
        this.touristSpotRegion = touristSpotEntity.getWriteRegion();
    }
}
