package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.DailyTravelDateEntity;
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
    
    public GetTouristProductResponseDto(TouristProductResultSet touristProductResultSet, List<DailyTravelDateEntity> dailyTravelDateEntities) {
        super("SU", "Success");

        List<DailyTravel> dailyTravelList = new ArrayList<DailyTravel>();

        for (DailyTravelDateEntity dailyTravelDateEntity: dailyTravelDateEntities) {
            DailyTravel dailyTravel = new DailyTravel(dailyTravelDateEntity);
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

    public DailyTravel(DailyTravelDateEntity dailyTravelDateEntity) {
        this.touristSpotNumber = dailyTravelDateEntity.getTouristSpotNumber();
        this.dailyTravelNumber = dailyTravelDateEntity.getDailyTravelNumber();
        this.dailyTravelDate = dailyTravelDateEntity.getDailyTravelDate();
        this.writeSequence = dailyTravelDateEntity.getSequence();
        this.touristSpotName = dailyTravelDateEntity.getWriteTouristSpotName();
        this.touristSpotImageUrl = dailyTravelDateEntity.getWriteImageUrl();
        this.touristSpotAddress = dailyTravelDateEntity.getWriteProductAddress();
    }
}
