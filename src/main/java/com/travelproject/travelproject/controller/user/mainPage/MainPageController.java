package com.travelproject.travelproject.controller.user.mainPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageBannerDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageProductDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageRecommendDto;
import com.travelproject.travelproject.service.MainPageService;

@RestController
@RequestMapping(RequestPattern.MAINPAGE_API)
public class MainPageController {
    
    private MainPageService mainPageService;

    @Autowired
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService=mainPageService;
    }

    //# 이미지 배너 조회
    @GetMapping("/banner")
    public ResponseEntity<? super GetMainPageBannerDto> getBannerList() {
        ResponseEntity<? super GetMainPageBannerDto> response = mainPageService.getBannerList();
        return response;
    }
    
    //# Top 3 여행지코스 상품 목록 조회
    @GetMapping("/product-board-top3")
    public ResponseEntity<? super GetMainPageProductDto> getProductBoardListTop3() {
        ResponseEntity<? super GetMainPageProductDto> response = mainPageService.getProductBoardListTop3();
        return response;
    }

    //# 최근 추천 여행지 목록 3개 조회
    @GetMapping("/recommend-tourist-spot-top3")
    public ResponseEntity<? super GetMainPageRecommendDto> getRecommendList3() {
        ResponseEntity<? super GetMainPageRecommendDto> response = mainPageService.getRecommendList3();
        return response;
    }

}
