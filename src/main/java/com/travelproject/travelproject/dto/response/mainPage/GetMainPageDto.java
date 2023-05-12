package com.travelproject.travelproject.dto.response.mainPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;
import com.travelproject.travelproject.entity.listEntity.RecommendResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetMainPageDto extends ResponseDto {
    List<TouristProductTopList> productTop3List;
    List<RecommendTourspotTopList> recommendTopList;

    public GetMainPageDto(List<ProductResultSet> productResultSet, List<RecommendResultSet> recommendResultSet) {
        super("SU", "SUCCESS");

        List<TouristProductTopList> productTop3List = new ArrayList<>();
        List<RecommendTourspotTopList> recommendTopList = new ArrayList<>();

        // 배너

        for (ProductResultSet result : productResultSet) {
            TouristProductTopList touristProductTopList = new TouristProductTopList(result);
            productTop3List.add(touristProductTopList);
        }
        this.productTop3List=productTop3List;

        for (RecommendResultSet result : recommendResultSet) {
            RecommendTourspotTopList recommendTourspotTopList = new RecommendTourspotTopList(result);
            recommendTopList.add(recommendTourspotTopList);
        }
        this.recommendTopList=recommendTopList;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class RecommendTourspotTopList {
    private String touristSpotTitle;
    private String recommendTouristSpotImageUrl;
    private String content;

    public RecommendTourspotTopList(RecommendResultSet recommendResultSet) {
        this.touristSpotTitle=recommendResultSet.getTouristSpotTitle();
        this.recommendTouristSpotImageUrl=recommendResultSet.getRecommendTouristSpotImageUrl();
        this.content=recommendResultSet.getContent();
    }
}
