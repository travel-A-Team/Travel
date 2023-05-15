package com.travelproject.travelproject.dto.response.admin.touristSpot;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.admin.TouristSpotEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTouristSpotResponseDto  extends ResponseDto{
    private int writeTouristSpotNumber;
    private String writeTouristSpotName;
    private String writeImageUrl;
    private String writeTouristSpotAddress;

    public GetTouristSpotResponseDto(TouristSpotEntity touristSpotEntity) {
        super("SU", "Success");
        this.writeTouristSpotNumber = touristSpotEntity.getTouristSpotNumber();
        this.writeTouristSpotName = touristSpotEntity.getWriteTouristSpotName();
        this.writeImageUrl = touristSpotEntity.getWriteImageUrl();
        this.writeTouristSpotAddress = touristSpotEntity.getWriteTouristSpotAddress();
    }
}
