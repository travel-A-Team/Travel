package com.travelproject.travelproject.entity.planner;

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
    private String plannerUserEmail;
    private String plannerImageUrl;
    private String plannerTitle;
    private String totalPlannerTravelSchedule;
    private String plannerTourRoute;
    private int plannerMoney;

    public PlannerEntity(String plannerUserEmail, PostPlannerBoardRequestDto dto, String plannerTourRouteCollection, String totalPlannerTravelSchedule) {
        this.plannerUserEmail = plannerUserEmail;
        this.plannerImageUrl = dto.getPlannerImageUrl();
        this.plannerTitle = dto.getPlannerTitle();
        this.totalPlannerTravelSchedule = totalPlannerTravelSchedule;
        this.plannerTourRoute = plannerTourRouteCollection;
        this.plannerMoney = dto.getPlannerMoney();
    }

}
