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
public class GetTouristProductWriteResponseDto extends ResponseDto {
    private List<TouristProduct> touristProductList;

    public GetTouristProductWriteResponseDto(
        List<ProductResultSet> productResultSet) {
        super("SU", "SUCCESS");

        this.touristProductList=TouristProduct.createList(productResultSet);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TouristProduct {
    private String productTitle;
    private String productTourRoute;
    private int productMoney;
    private String productImageUrl;

    public TouristProduct(ProductResultSet resultSet) {
        this.productTitle=resultSet.getProductTitle();
        this.productTourRoute=resultSet.getProductTourRoute();
        this.productMoney=resultSet.getProductMoney();
        this.productImageUrl=resultSet.getProductImageUrl();
    }

    static List<TouristProduct> createList(List<ProductResultSet> productResultSet) {
        List<TouristProduct> productList = new ArrayList<>();

        for (ProductResultSet result : productResultSet) {
            TouristProduct touristProductList = new TouristProduct(result);
            productList.add(touristProductList);
        }

        return productList;
    }
}