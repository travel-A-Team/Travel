package com.travelproject.travelproject.dto.request.plannerBoard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPlannerTravelSpotRequestDto {
    @NotNull
    private Integer plannerPlannerNumber;
    @NotNull
    private Integer touristSpotWriteTouristSpotNumber;
    @NotBlank
    private String plannerTravelDate;
    @NotBlank
    private String writeImageUrl;
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private String writePlannerAddress;
}
