package com.travelproject.travelproject.dto.response.plannerBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.PlannerDailyTravelDateEntity;
import com.travelproject.travelproject.entity.PlannerEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPlannerResponseDto extends ResponseDto {
    private String plannerTitle;
    private String totalPlannerTravelSchedule;
    private String plannerTourRoute;
    private int plannerMoney;
    private List<PlannerTravelSpot> plannerTravelSpotList;

    public GetPlannerResponseDto(PlannerEntity plannerEntity,
            List<PlannerDailyTravelDateEntity> plannerDailyTravelDateEntity) {
        super("SU", "Success");
        this.plannerTitle = plannerEntity.getTitle();
        this.totalPlannerTravelSchedule = plannerEntity.getTotalTravelSchedule();
        this.plannerTourRoute = plannerEntity.getTourRoute();
        this.plannerMoney = plannerEntity.getMoney();
        this.plannerTravelSpotList = PlannerTravelSpot.createList(plannerDailyTravelDateEntity);

    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class PlannerTravelSpot {
    private int plannerDailyNumber;
    private String plannerTravelDate;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writePlannerAddress;

    PlannerTravelSpot(PlannerDailyTravelDateEntity plannerDailyTravelDateEntity) {
        this.plannerDailyNumber = plannerDailyTravelDateEntity.getPlannerDailyNumber();
        this.plannerTravelDate = plannerDailyTravelDateEntity.getTravelDate();
        this.writeImageUrl = plannerDailyTravelDateEntity.getTouristSpotImageUrl();
        this.writeTouristSpotName = plannerDailyTravelDateEntity.getTouristSpotName();
        this.writePlannerAddress = plannerDailyTravelDateEntity.getTouristSpotAddress();
    }

    static List<PlannerTravelSpot> createList(List<PlannerDailyTravelDateEntity> plannerDailyTravelDateEntities) {
        List<PlannerTravelSpot> plannerTravelSpotList = new ArrayList<>();
        for (PlannerDailyTravelDateEntity plannerDailyTravelDateEntity : plannerDailyTravelDateEntities) {
            PlannerTravelSpot plannerTravelSpot = new PlannerTravelSpot(plannerDailyTravelDateEntity);
            plannerTravelSpotList.add(plannerTravelSpot);
        }

        return plannerTravelSpotList;
    }
}