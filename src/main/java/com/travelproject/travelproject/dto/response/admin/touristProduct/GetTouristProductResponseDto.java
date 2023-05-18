package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.List;

import lombok.Data;

@Data
public class GetTouristProductResponseDto {
    private String productTitle;
    private String productTotalSchedule;
    private String productTourRoute;
    private int productMoney;
    private int likeCount;
    private List<DailyTravel> dailyTravelList;    
}

@Data
class DailyTravel {
    private String dailyTravelNumber;
    private String dailyTravelDate;
    private String touristSpotName;
    private String touristSpotImageUrl;
    private String touristSpotAddress;
}
