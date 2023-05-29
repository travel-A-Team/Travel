package com.travelproject.travelproject.dto.response.mainPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetMainPageProductDto extends ResponseDto {

    private List<TouristProductTopList> productTop3List;

    public GetMainPageProductDto(List<ProductResultSet> productResultSet, List<LikeyEntity> likeyEntities) {
        super("SU", "SUCCESS");
        
        this.productTop3List=TouristProductTopList.createList(productResultSet);
        
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TouristProductTopList {
    private int productNumber;
    private String productTitle;
    private String productImageUrl;
    private int productMoney;
    private int likeCount;

    public TouristProductTopList(ProductResultSet productResultSet) {
        this.productNumber=productResultSet.getProductNumber();
        this.productTitle=productResultSet.getProductTitle();
        this.productImageUrl=productResultSet.getProductImageUrl();
        this.productMoney=productResultSet.getProductMoney();
        this.likeCount=productResultSet.getLikeyCount();
    }

    static List<TouristProductTopList> createList(List<ProductResultSet> productResultSet) {
        List<TouristProductTopList> productTop3List = new ArrayList<>();
        
        for (ProductResultSet result : productResultSet) {
            TouristProductTopList touristProductTopList = new TouristProductTopList(result);
            productTop3List.add(touristProductTopList);
        }

        return productTop3List;
    }
}