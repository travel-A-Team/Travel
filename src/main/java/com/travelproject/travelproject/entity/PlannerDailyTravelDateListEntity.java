package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PlannerDailyTravelDate")
@Table(name = "PlannerDailyTravelDate")
public class PlannerDailyTravelDateListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plannerDailyNumber;
    private int PlannerPlannerNumber;
    private int TouristSpotWriteTouristSpotNumber;
    private String plannerTravelDate;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writePlannerAddress;
}
