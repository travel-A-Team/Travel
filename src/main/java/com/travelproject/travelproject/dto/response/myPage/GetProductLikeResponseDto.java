package com.travelproject.travelproject.dto.response.myPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetProductLikeResponseDto extends ResponseDto {
    
   List<Product> userLikeProuductList;

   public GetProductLikeResponseDto(List<TouristProductEntity> touristProductEntities) {
        super("SU", "Success");
        List<Product> userLikeProuductList = new ArrayList<>();

        for(TouristProductEntity touristProductEntity: touristProductEntities) {
            Product product = new Product(touristProductEntity);

            userLikeProuductList.add(product);
        }

        this.userLikeProuductList = userLikeProuductList;

   }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Product {
    private int productNumber;
    private String productImageUrl;
    private String productTitle;
    private int productMoney;
    private String productTourRoute;

    public Product(TouristProductEntity touristProductEntity) {
        this.productNumber = touristProductEntity.getProductNumber();
        this.productImageUrl = touristProductEntity.getImageUrl();
        this.productTitle = touristProductEntity.getTitle();
        this.productMoney = touristProductEntity.getMoney();
        this.productTourRoute = touristProductEntity.getTourRoute();
    }

}
