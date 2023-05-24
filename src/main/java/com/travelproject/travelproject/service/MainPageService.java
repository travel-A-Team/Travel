package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.mainPage.GetMainPageBannerDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageProductDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageRecommendDto;

public interface MainPageService {
    
    public ResponseEntity<? super GetMainPageBannerDto> getBannerList();

    public ResponseEntity<? super GetMainPageProductDto> getProductBoardListTop3();

    public ResponseEntity<? super GetMainPageRecommendDto> getRecommendList3();

}
