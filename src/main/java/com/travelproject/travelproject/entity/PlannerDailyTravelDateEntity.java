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
    private int plannerNumber;
    private int touristSpotNumber;
    private String travelDate;
    private String touristSpotImageUrl;
    private String touristSpotName;
    private String touristSpotAddress;
    private int sequence;

    public PlannerDailyTravelDateEntity(int plannerNumber, String plannerTravelDate, int sequence,
            TouristSpotEntity touristSpotWriteEntity, PostPlannerBoardRequestDto dto) {
        this.plannerNumber = plannerNumber;
        this.touristSpotNumber = touristSpotWriteEntity.getTouristSpotNumber();
        this.travelDate = plannerTravelDate;
        this.touristSpotImageUrl = touristSpotWriteEntity.getImageUrl();
        this.touristSpotName = touristSpotWriteEntity.getTouristSpotName();
        this.touristSpotAddress = touristSpotWriteEntity.getTouristSpotAddress();
        this.sequence = sequence;

    }

    public PlannerDailyTravelDateEntity(PatchPlannerBoardRequestDto dto, int touristSpotWriteTouristSpotNumber, String plannerTravelDate, String writeImageUrl, String writeTouristSpotName, String writePlannerAddress, int sequence) {
        this.plannerNumber = dto.getPlannerNumber();
        this.touristSpotNumber = touristSpotWriteTouristSpotNumber;
        this.travelDate = plannerTravelDate;
        this.touristSpotImageUrl = writeImageUrl;
        this.touristSpotName = writeTouristSpotName;
        this.touristSpotAddress = writePlannerAddress;
        this.sequence = sequence;

    }

}
