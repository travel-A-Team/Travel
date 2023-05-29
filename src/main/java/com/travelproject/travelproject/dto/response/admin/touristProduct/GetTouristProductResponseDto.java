package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.DailyTravelDateEntity;
import com.travelproject.travelproject.entity.resultSet.DailyResultSet;
import com.travelproject.travelproject.entity.resultSet.TouristProductResultSet;

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

        for (DailyResultSet dailyTravelDateSet: dailyResultSetList) {
            DailyTravel dailyTravel = new DailyTravel(dailyTravelDateSet);
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
    private int touristSpotNumber;
    private int dailyTravelNumber;
    private String dailyTravelDate;
    private int writeSequence;
    private String touristSpotName;
    private String touristSpotImageUrl;
    private String touristSpotAddress;

    public DailyTravel(DailyResultSet dailyTravelDateSet) {
        this.touristSpotNumber = dailyTravelDateSet.getTouristSpotNumber();
        this.dailyTravelNumber = dailyTravelDateSet.getDailyTravelNumber();
        this.dailyTravelDate = dailyTravelDateSet.getDailyTravelDate();
        this.writeSequence = dailyTravelDateSet.getwriteSequence();
        this.touristSpotName = dailyTravelDateSet.getWriteTouristSpotName();
        this.touristSpotImageUrl = dailyTravelDateSet.getTouristSpotImageUrl();
        this.touristSpotAddress = dailyTravelDateSet.getTouristSpotAddress();
    }
}
