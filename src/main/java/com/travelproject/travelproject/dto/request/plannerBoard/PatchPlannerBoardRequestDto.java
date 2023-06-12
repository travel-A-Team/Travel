package com.travelproject.travelproject.dto.request.plannerBoard;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchPlannerBoardRequestDto extends ResponseDto {
    @NotNull
    private Integer plannerNumber;
    @NotBlank
    private String plannerTitle;
    @NotNull
    private Integer plannerMoney;
    @NotNull
    private List<PatchPlannerTravelSpotRequestDto> plannerTravelSpotList;
    private List<DeleteDailySpotNumber> deleteDailySpotNumberList;
}
