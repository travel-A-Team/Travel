package com.travelproject.travelproject.dto.response.admin.main;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.TouristProductEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetTouristProductListResponseDto extends ResponseDto {
    private List<TouristProductSummary> touristProductList;

    public GetTouristProductListResponseDto(List<TouristProductEntity> touristProductEntities) {

        super("SU", "Success");
        
        List<TouristProductSummary> touristProductList = new ArrayList<>();

        for (TouristProductEntity touristProductEntity: touristProductEntities) {
            TouristProductSummary touristProductSummary = new TouristProductSummary(touristProductEntity);
            touristProductList.add(touristProductSummary);
        }

        this.touristProductList = touristProductList;
    }
}

@Data
@NoArgsConstructor
class TouristProductSummary {
    private String representativeImageUrl;
    private String productTitle;
    private int productAmount;

    public TouristProductSummary(TouristProductEntity touristProductEntity) {
        this.representativeImageUrl = touristProductEntity.getImageUrl();
        this.productTitle = touristProductEntity.getTitle();
        this.productAmount = touristProductEntity.getMoney();
    }
}
