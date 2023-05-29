package com.travelproject.travelproject.dto.response.admin.touristSpot;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristSpotEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetTouristSpotListResponseDto  extends ResponseDto{
    List<TouristSpotSummary> touristSpotList;

    public GetTouristSpotListResponseDto(List<TouristSpotEntity> touristSpotEntities) {
        super("SU", "Success");
        List<TouristSpotSummary> touristSpotList = new ArrayList<TouristSpotSummary>();

        for (TouristSpotEntity touristSpotEntity: touristSpotEntities) {
            TouristSpotSummary touristSpotSummary = new TouristSpotSummary(touristSpotEntity);
            touristSpotList.add(touristSpotSummary);
        }

        this.touristSpotList = touristSpotList;
    }
}

@Data
@NoArgsConstructor
class TouristSpotSummary {
    private int writeTouristSpotNumber;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writeTouristSpotAddress;
    private String writeTouristSpotDate;

    public TouristSpotSummary(TouristSpotEntity touristSpotEntity) {
        this.writeTouristSpotNumber = touristSpotEntity.getTouristSpotNumber();
        this.writeImageUrl = touristSpotEntity.getImageUrl();
        this.writeTouristSpotName = touristSpotEntity.getTouristSpotName();
        this.writeTouristSpotAddress = touristSpotEntity.getTouristSpotAddress();
        this.writeTouristSpotDate = touristSpotEntity.getWriteDate();
    }
}
