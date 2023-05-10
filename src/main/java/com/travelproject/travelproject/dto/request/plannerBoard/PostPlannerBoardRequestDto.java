package com.travelproject.travelproject.dto.request.plannerBoard;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.travelproject.travelproject.entity.PlannerDailyTravelDateListEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPlannerBoardRequestDto {
    @NotBlank
    @Email
    private String plannerUserEmail;
    @NotBlank
    private String plannerTitle;
    @NotBlank
    private String totalPlannerTravelSchedule;
    @NotBlank
    private Integer plannerMoney;
    @NotBlank
    private List<PlannerDailyTravelDateListEntity> plannerDailyTravelDate;    
}
