package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.plannerBoard.PatchPlannerBoardRequestDto;
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
    private int sequence;

    public PlannerDailyTravelDateEntity(int plannerNumber, String plannerTravelDate, int sequence,
            TouristSpotEntity touristSpotWriteEntity, PostPlannerBoardRequestDto dto) {
        this.plannerPlannerNumber = plannerNumber;
        this.touristSpotWriteTouristSpotNumber = touristSpotWriteEntity.getTouristSpotNumber();
        this.plannerTravelDate = plannerTravelDate;
        this.writeImageUrl = touristSpotWriteEntity.getWriteImageUrl();
        this.writeTouristSpotName = touristSpotWriteEntity.getWriteTouristSpotName();
        this.writePlannerAddress = touristSpotWriteEntity.getWriteTouristSpotAddress();
        this.sequence = sequence;

    }

    public PlannerDailyTravelDateEntity(PatchPlannerBoardRequestDto dto, int touristSpotWriteTouristSpotNumber, String plannerTravelDate, String writeImageUrl, String writeTouristSpotName, String writePlannerAddress, int sequence) {
        this.plannerPlannerNumber = dto.getPlannerNumber();
        this.touristSpotWriteTouristSpotNumber = touristSpotWriteTouristSpotNumber;
        this.plannerTravelDate = plannerTravelDate;
        this.writeImageUrl = writeImageUrl;
        this.writeTouristSpotName = writeTouristSpotName;
        this.writePlannerAddress = writePlannerAddress;
        this.sequence = sequence;

    }

}
