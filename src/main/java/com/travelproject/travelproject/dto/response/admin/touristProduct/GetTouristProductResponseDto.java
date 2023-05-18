package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.listEntity.DailyResultSet;
import com.travelproject.travelproject.entity.listEntity.TouristProductResultSet;

import lombok.Data;

@Data
public class GetTouristProductResponseDto extends ResponseDto{
    private int productNumber;
    private String productTitle;
    private String productTotalSchedule;
    private String productTourRoute;
    private int productMoney;
    private int likeCount;
    private List<DailyTravel> dailyTravelList;    
    
    public GetTouristProductResponseDto(TouristProductResultSet touristProductResultSet, List<DailyResultSet> dailyResultSetList) {
        super("SU", "Success");

        List<DailyTravel> dailyTravelList = new ArrayList<DailyTravel>();

        for (DailyResultSet dailyResultSet: dailyResultSetList) {
            DailyTravel dailyTravel = new DailyTravel(dailyResultSet);
            dailyTravelList.add(dailyTravel);
        }

        this.productNumber = touristProductResultSet.getProductNumber();
        this.productTitle = touristProductResultSet.getProductTitle();
        this.productTotalSchedule = touristProductResultSet.getProductTotalSchedule();
        this.productTourRoute = touristProductResultSet.getProductTourRoute();
        this.productMoney = touristProductResultSet.getProductMoney();
        this.likeCount = touristProductResultSet.getLikeCount();
        this.dailyTravelList = dailyTravelList;

    }
}

@Data
class DailyTravel {
    private int dailyTravelNumber;
    private String dailyTravelDate;
    private String touristSpotName;
    private String touristSpotImageUrl;
    private String touristSpotAddress;

    public DailyTravel(DailyResultSet dailyResultSet) {
        this.dailyTravelNumber = dailyResultSet.getDailyTravelNumber();
        this.dailyTravelDate = dailyResultSet.getDailyTravelDate();
        this.touristSpotName = dailyResultSet.getWriteTouristSpotName();
        this.touristSpotImageUrl = dailyResultSet.getWriteImageUrl();
        this.touristSpotAddress = dailyResultSet.getWriteProductAddress();
    }
}
