package com.travelproject.travelproject.controller.user.mainPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageDto;
import com.travelproject.travelproject.service.MainPageService;

@RestController
@RequestMapping(RequestPattern.MAINPAGE_API)
public class MainPageController {
    
    private MainPageService mainPageProductService;

    @Autowired
    public MainPageController(MainPageService mainPageProductService) {
        this.mainPageProductService=mainPageProductService;
    }

    // 이미지 배너 추가해야 되고, 매핑 한페이지니깐 1개로 통일

    //# Top 3 여행지코스 상품 목록 조회
    @GetMapping("/product-board-top3")
    public ResponseEntity<? super GetMainPageDto> getProductBoardListTop3() {
        ResponseEntity<? super GetMainPageDto> response =
            mainPageProductService.getProductBoardListTop3();
        return response;
    }

    //# 최근 추천 여행지 목록 3개 조회
    @GetMapping("/recommend-tourlist-spot-top3")
    public ResponseEntity<? super GetMainPageDto> getRecommendList3() {
        ResponseEntity<? super GetMainPageDto> response =
            mainPageProductService.getRecommendList3();
        return response;
    }

}
