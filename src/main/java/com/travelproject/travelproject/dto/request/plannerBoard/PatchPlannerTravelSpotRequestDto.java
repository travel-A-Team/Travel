package com.travelproject.travelproject.dto.request.plannerBoard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchPlannerTravelSpotRequestDto {
    private int plannerDailySpotNumber;
    @NotNull
    private int touristSpotWriteTouristSpotNumber;
    @NotBlank
    private String plannerTravelDate;
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private int sequence;
}
