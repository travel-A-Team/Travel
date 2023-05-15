package com.travelproject.travelproject.dto.response.mainPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetMainPageProductDto extends ResponseDto {
    List<TouristProductTopList> productTop3List;    
    // 배너

    public GetMainPageProductDto(List<ProductResultSet> productResultSet) {
        super("SU", "SUCCESS");

        List<TouristProductTopList> productTop3List = new ArrayList<>();
       
        for (ProductResultSet result : productResultSet) {
            TouristProductTopList touristProductTopList = new TouristProductTopList(result);
            productTop3List.add(touristProductTopList);
        }
        this.productTop3List=productTop3List;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TouristProductTopList {
    private String productTitle;
    private String productTourRoute;
    private String productImageUrl;
    private int productMoney;
    private int likeCount;

    public TouristProductTopList(ProductResultSet productResultSet) {
        this.productTitle=productResultSet.getProductTitle();
        this.productTourRoute=productResultSet.getProductTourRoute();
        this.productImageUrl=productResultSet.getProductImageUrl();
        this.productMoney=productResultSet.getProductMoney();
        this.likeCount=productResultSet.getLikeCount();
    }
}