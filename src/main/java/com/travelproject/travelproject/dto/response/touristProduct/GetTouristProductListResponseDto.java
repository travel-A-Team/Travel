package com.travelproject.travelproject.dto.response.touristProduct;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetTouristProductListResponseDto extends ResponseDto {
    private List<TouristProductList> touristProduct;

    public GetTouristProductListResponseDto(List<ProductResultSet> productResultSet) {
        super("SU", "SUCCESS");

        List<TouristProductList> touristProduct = new ArrayList<>();

        for (ProductResultSet result : productResultSet) {
            TouristProductList touristProductList = new TouristProductList(result);
            touristProduct.add(touristProductList);
        }

        this.touristProduct=touristProduct;
    }
    
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TouristProductList {
    private String productTitle;
    private String productTourRoute;
    private int productMoney;
    private String productImageUrl;

    public TouristProductList(ProductResultSet resultSet) {
        this.productTitle=resultSet.getProductTitle();
        this.productTourRoute=resultSet.getProductTourRoute();
        this.productMoney=resultSet.getProductMoney();
        this.productImageUrl=resultSet.getProductImageUrl();
    }
}
