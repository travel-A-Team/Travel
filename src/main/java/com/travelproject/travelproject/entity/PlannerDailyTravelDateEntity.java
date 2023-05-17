package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.plannerBoard.PostPlannerBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Plannerdailytraveldate")
@Table(name = "Plannerdailytraveldate")
public class PlannerDailyTravelDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plannerDailyNumber;
    private int plannerPlannerNumber;
    private int touristSpotWriteTouristSpotNumber;
    private String plannerTravelDate;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writePlannerAddress;

    public PlannerDailyTravelDateEntity(int plannerNumber, String plannerTravelDate,
            TouristSpotEntity touristSpotWriteEntity, PostPlannerBoardRequestDto dto) {
        this.plannerPlannerNumber = plannerNumber;
        this.touristSpotWriteTouristSpotNumber = touristSpotWriteEntity.getTouristSpotNumber();
        this.plannerTravelDate = plannerTravelDate;
        this.writeImageUrl = touristSpotWriteEntity.getWriteImageUrl();
        this.writeTouristSpotName = touristSpotWriteEntity.getWriteTouristSpotName();
        this.writePlannerAddress = touristSpotWriteEntity.getWriteTouristSpotAddress();

    }

}
