package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageProductDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageRecommendDto;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;
import com.travelproject.travelproject.entity.listEntity.RecommendResultSet;
import com.travelproject.travelproject.repository.mainPage.ProductRepository;
import com.travelproject.travelproject.repository.mainPage.RecommendRepository;
import com.travelproject.travelproject.service.MainPageService;

@Service
public class MainPageServiceImplement implements MainPageService {

    private ProductRepository productRepository;
    private RecommendRepository recommendRepository;

    @Autowired
    public MainPageServiceImplement(ProductRepository productRepository, RecommendRepository recommendRepository) {
        this.productRepository=productRepository;
        this.recommendRepository=recommendRepository;
    }

    //! Top3 조회
    @Override
    public ResponseEntity<? super GetMainPageProductDto> getProductBoardListTop3() {
        GetMainPageProductDto body = null;

        try {
            List<ProductResultSet> productResultSet = productRepository.getProductTop3();

            

            body = new GetMainPageProductDto(productResultSet);
            // Top3 값들 받아서 body에 담아주면 됨

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    
    //! 추천여행지 3개 조회
    @Override
    public ResponseEntity<? super GetMainPageRecommendDto> getRecommendList3() {
        GetMainPageRecommendDto body = null;
        
        try {
            List<RecommendResultSet> recommendResultSet = recommendRepository.getRecommendList3();
            body = new GetMainPageRecommendDto(recommendResultSet);
            // 최근 추천 여행지 3개 response 값들 받아서 body에 담아주면 됨

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
    


