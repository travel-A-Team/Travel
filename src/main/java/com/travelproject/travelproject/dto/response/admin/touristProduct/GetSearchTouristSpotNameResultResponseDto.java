package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristSpotEntity;

import lombok.Data;

@Data
public class GetSearchTouristSpotNameResultResponseDto extends ResponseDto {
    List<TouristSpotDto> touristSpotList;

    public GetSearchTouristSpotNameResultResponseDto(List<TouristSpotEntity> touristSpotEntities) {
        
        super("SU", "Success");

        List<TouristSpotDto> touristSpotList = new ArrayList<TouristSpotDto>();

        for (TouristSpotEntity touristSpotEntity: touristSpotEntities) {
            TouristSpotDto touristSpot = new TouristSpotDto(touristSpotEntity);
            touristSpotList.add(touristSpot);
        }

        this.touristSpotList = touristSpotList;

    }
}
