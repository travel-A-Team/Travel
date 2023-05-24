package com.travelproject.travelproject.dto.response.mainPage;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.BannerEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetMainPageBannerDto extends ResponseDto {

    private List<BannerList> bannerList;

    public GetMainPageBannerDto(List<BannerEntity> bannerEntity) {
        super("SU", "Success");

        List<BannerList> bannerList = new ArrayList<>();

        for (BannerEntity result : bannerEntity) {
            BannerList mainBannerList = new BannerList(result);
            bannerList.add(mainBannerList);
        }

        this.bannerList = bannerList;

    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class BannerList {
    private String imageUrl;
    private String content;

    public BannerList(BannerEntity bannerEntity) {
        this.imageUrl = bannerEntity.getImageUrl();
        this.content = bannerEntity.getContent();
    }

}
