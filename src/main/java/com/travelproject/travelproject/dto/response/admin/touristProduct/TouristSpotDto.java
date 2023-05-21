package com.travelproject.travelproject.dto.response.admin.touristProduct;

import com.travelproject.travelproject.entity.TouristSpotEntity;

import lombok.Data;

@Data
class TouristSpotDto {
    private int touristSpotNumber;
    private String  touristSpotImageUrl;
    private String  touristSpotName;
    private String  touristSpotAddress;
    private String  touristSpotRegion;

    public TouristSpotDto(TouristSpotEntity touristSpotEntity) {
        this.touristSpotNumber = touristSpotEntity.getTouristSpotNumber();
        this.touristSpotImageUrl = touristSpotEntity.getWriteImageUrl();
        this.touristSpotName = touristSpotEntity.getWriteTouristSpotName();
        this.touristSpotAddress = touristSpotEntity.getWriteTouristSpotAddress();
        this.touristSpotRegion = touristSpotEntity.getWriteRegion();
    }
}


