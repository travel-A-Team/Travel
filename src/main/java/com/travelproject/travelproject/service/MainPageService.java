package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.mainPage.GetMainPageDto;

public interface MainPageService {
    
    public ResponseEntity<? super GetMainPageDto> getProductBoardListTop3();

    public ResponseEntity<? super GetMainPageDto> getRecommendList3();

}
