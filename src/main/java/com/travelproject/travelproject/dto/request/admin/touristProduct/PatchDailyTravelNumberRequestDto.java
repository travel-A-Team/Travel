package com.travelproject.travelproject.dto.request.admin.touristProduct;

import java.util.List;

import lombok.Data;

@Data
public class PatchDailyTravelNumberRequestDto {
    List<DeleteDailyTravelNumber> deleteDailyTravelNumberList;
}




