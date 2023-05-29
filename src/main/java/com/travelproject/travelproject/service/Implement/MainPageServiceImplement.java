package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageBannerDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageProductDto;
import com.travelproject.travelproject.dto.response.mainPage.GetMainPageRecommendDto;
import com.travelproject.travelproject.entity.BannerEntity;
import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;
import com.travelproject.travelproject.repository.BannerRepository;
import com.travelproject.travelproject.repository.LikeyRepository;
import com.travelproject.travelproject.repository.RecommendationTouristSpotRepositroy;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.service.MainPageService;

@Service
public class MainPageServiceImplement implements MainPageService {

    private TouristProductRepository touristProductRepository;
    private RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy;
    private LikeyRepository likeyRepository;
    private BannerRepository bannerRepository;

    @Autowired
    public MainPageServiceImplement(TouristProductRepository touristProductRepository,
            RecommendationTouristSpotRepositroy recommendationTouristSpotRepositroy, LikeyRepository likeyRepository,
            BannerRepository bannerRepository) {
        this.touristProductRepository = touristProductRepository;
        this.recommendationTouristSpotRepositroy = recommendationTouristSpotRepositroy;
        this.likeyRepository = likeyRepository;
        this.bannerRepository = bannerRepository;
    }

    //! 배너 조회
    @Override
    public ResponseEntity<? super GetMainPageBannerDto> getBannerList() {

        GetMainPageBannerDto body = null;

        try {

            List<BannerEntity> bannerEntities = bannerRepository.getBannerList();
            body = new GetMainPageBannerDto(bannerEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    //! Top3 조회
    @Override
    public ResponseEntity<? super GetMainPageProductDto> getProductBoardListTop3() {
        GetMainPageProductDto body = null;

        try {
            List<LikeyEntity> likeyEntities = null;
            List<ProductResultSet> productResultSet = touristProductRepository.getProductTop3();

            for (int count = 0; count < productResultSet.size(); count++) {
                int likeyProduct = productResultSet.get(count).getProductNumber();
                likeyEntities = likeyRepository.findByProductNumber(likeyProduct);
            }

            body = new GetMainPageProductDto(productResultSet, likeyEntities);

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
            List<RecommendResultSet> recommendResultSet = recommendationTouristSpotRepositroy.getRecommendList3();
            body = new GetMainPageRecommendDto(recommendResultSet);
            // 최근 추천 여행지 3개 response 값들 받아서 body에 담아주면 됨

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
