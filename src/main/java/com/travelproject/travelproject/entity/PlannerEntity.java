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
@Entity(name = "Planner")
@Table(name = "Planner")
public class PlannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plannerNumber;
    private String userEmail;
    private String imageUrl;
    private String title;
    private String totalTravelSchedule;
    private String tourRoute;
    private int money;

    public PlannerEntity(String plannerUserEmail, PostPlannerBoardRequestDto dto, String plannerTourRouteCollection, String totalPlannerTravelSchedule, String imageUrl) {
        this.userEmail = plannerUserEmail;
        this.imageUrl = imageUrl;
        this.title = dto.getPlannerTitle();
        this.totalTravelSchedule = totalPlannerTravelSchedule;
        this.tourRoute = plannerTourRouteCollection;
        this.money = dto.getPlannerMoney();
    }

}
