package com.travelproject.travelproject.dto.response.admin.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.TouristProductListResultSet;

import lombok.Data;

@Data
public class GetTouristProductListResponseDto extends ResponseDto {
    List<TouristProduct> touristProductList;

    public GetTouristProductListResponseDto(List<TouristProductListResultSet> touristProductListResultSetList) {
        super("SU", "Success");

        List<TouristProduct> touristProductList = new ArrayList<TouristProduct>();

        for (TouristProductListResultSet touristProductListResultSet: touristProductListResultSetList) {
            TouristProduct touristProduct = new TouristProduct(touristProductListResultSet);
            touristProductList.add(touristProduct);
        }
        
        this.touristProductList = touristProductList;
    }
}

@Data
class TouristProduct {
    private int productNumber;
    private String productImageUrl;
    private String productTitle;
    private String productTotalSchedule;
    private String prodcutTourRoute;
    private int productMoney;
    private int likeCount;
    private String productWriteDate;

    public TouristProduct(TouristProductListResultSet touristProductListResultSet) {
        this.productNumber = touristProductListResultSet.getProductNumber();
        this.productImageUrl = touristProductListResultSet.getProductImageUrl();
        this.productTitle = touristProductListResultSet.getProductTitle();
        this.productTotalSchedule = touristProductListResultSet.getProductTotalSchedule();
        this.prodcutTourRoute = touristProductListResultSet.getProductTourRoute();
        this.productMoney = touristProductListResultSet.getProductMoney();
        this.likeCount = touristProductListResultSet.getLikeCount();
        this.productWriteDate = touristProductListResultSet.getProductWriteDate();
    }
}
