package com.travelproject.travelproject.dto.response.plannerBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.PlannerBoardResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetPlannerListResponseDto extends ResponseDto {
    private List<PlannerBoardSummary> plannerBoardList;

    public GetPlannerListResponseDto(List<PlannerBoardResultSet> resultSet) {

        super("SU", "Success");

        List<PlannerBoardSummary> plannerBoardList = new ArrayList<>();

        for (PlannerBoardResultSet result : resultSet) {
            PlannerBoardSummary boardSummary = new PlannerBoardSummary(result);
            plannerBoardList.add(boardSummary);
        }

        this.plannerBoardList = plannerBoardList;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class PlannerBoardSummary {
    private int plannerNumber;
    private String plannerTitle;
    private String plannerTourRoute;
    private int plannerMoney;
    private String plannerImageUrl;

    public PlannerBoardSummary(PlannerBoardResultSet resultSet) {
        this.plannerNumber = resultSet.getPlannerNumber();
        this.plannerTitle = resultSet.getPlannerTitle();
        this.plannerTourRoute = resultSet.getPlannerTourRoute();
        this.plannerMoney = resultSet.getPlannerMoney();
        this.plannerImageUrl = resultSet.getPlannerImageUrl();
    }

}
