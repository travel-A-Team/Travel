package com.travelproject.travelproject.dto.response.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.resultSet.DailyResultSet;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTouristProductResponseDto extends ResponseDto {
    private int productNumber;
    private String productTitle;
    private String productTourRoute;
    private int productMoney;
    private String productTotalSchedule;
    private int likeyCount;
    private List<ProductDailyList> productDailyList;

    public GetTouristProductResponseDto
            (TouristProductEntity touristProductEntity, ProductResultSet productResultSet, 
            List<DailyResultSet> dailyResultSet) {
        super("SU", "SUCCESS");
        this.productNumber = touristProductEntity.getProductNumber();
        this.productTitle=touristProductEntity.getTitle();
        this.productTourRoute=touristProductEntity.getTourRoute();
        this.productMoney=touristProductEntity.getMoney();
        this.productTotalSchedule=touristProductEntity.getTotalSchedule();
        this.likeyCount=productResultSet.getLikeyCount();
        this.productDailyList=ProductDailyList.createList(dailyResultSet);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ProductDailyList {
    private int dailyTravelNumber;
    private String dailyTravelDate;
    private String writeTouristSpotName;
    private String writeImageUrl;
    private String writeProductAddress;

    public ProductDailyList(DailyResultSet dailyResultSet) {
        this.dailyTravelNumber=dailyResultSet.getDailyTravelNumber();
        this.dailyTravelDate=dailyResultSet.getDailyTravelDate();
        this.writeTouristSpotName=dailyResultSet.getWriteTouristSpotName();
        this.writeImageUrl=dailyResultSet.getTouristSpotImageUrl();
        this.writeProductAddress=dailyResultSet.getTouristSpotAddress();
    }

    static List<ProductDailyList> createList(List<DailyResultSet> dailyResultSet) {
        List<ProductDailyList> DailyList = new ArrayList<>();

        for (DailyResultSet result : dailyResultSet) {
            ProductDailyList productDailyList = new ProductDailyList(result);
            DailyList.add(productDailyList);
        }

        return DailyList;
    }
}