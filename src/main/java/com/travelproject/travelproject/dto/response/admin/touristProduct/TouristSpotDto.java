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
        this.touristSpotImageUrl = touristSpotEntity.getImageUrl();
        this.touristSpotName = touristSpotEntity.getTouristSpotName();
        this.touristSpotAddress = touristSpotEntity.getTouristSpotAddress();
        this.touristSpotRegion = touristSpotEntity.getRegion();
    }
}


