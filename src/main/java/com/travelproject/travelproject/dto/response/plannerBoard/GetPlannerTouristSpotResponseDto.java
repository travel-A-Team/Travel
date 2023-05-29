package com.travelproject.travelproject.dto.response.plannerBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.entity.resultSet.PlannerWriteSpotResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetPlannerTouristSpotResponseDto extends ResponseDto {
    private List<TouristSpot> touristSpotList;

    public GetPlannerTouristSpotResponseDto(List<TouristSpotEntity> resultSet) {

        super("SU", "Success");

        List<TouristSpot> touristSpotList = new ArrayList<>();

        for (TouristSpotEntity result : resultSet) {
            TouristSpot touristSpot = new TouristSpot(result);
            touristSpotList.add(touristSpot);
        }

        this.touristSpotList = touristSpotList;
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

    public TouristSpot(TouristSpotEntity resultSet) {
        this.writeImageUrl = resultSet.getImageUrl();
        this.writeTouristSpotName = resultSet.getTouristSpotName();
        this.writeTouristSpotAddress = resultSet.getTouristSpotAddress();
        this.latitude = resultSet.getLatitude();
        this.longitude = resultSet.getLongitude();
    }
}
