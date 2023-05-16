package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.recommendationTouristSpot.PostRecommendationTouristSpotRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Recommendationtouristspot")
@Table(name = "Recommendationtouristspot")
public class RecommendationTouristSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int touristSpotNumber;
    private String recommendTouristSpotImageUrl; 
    private String touristSpotTitle; // 여행지 추천 제목
    private String content;
    private String touristSpotAddress;
    private String touristSpotUseTime;
    private String touristSpotInformation;
    private String recommendTouristSpotWriteDate;
    private String recommendTouristRegion;

    public RecommendationTouristSpotEntity(String recommendTouristRegion, PostRecommendationTouristSpotRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String recommendTouristSpotWriteDate = simpleDateFormat.format(now);

        this.recommendTouristSpotImageUrl = dto.getRecommendTouristImageUrl();
        this.touristSpotTitle = dto.getRecommendTouristSpotTitle();
        this.content = dto.getContent();
        this.touristSpotAddress = dto.getRecommendTouristAddress();
        this.touristSpotUseTime = dto.getRecommendTouristUseTime();
        this.touristSpotInformation = dto.getRecommendTouristInformation();
        this.recommendTouristSpotWriteDate = recommendTouristSpotWriteDate;
        this.recommendTouristRegion = recommendTouristRegion;
    }
}
