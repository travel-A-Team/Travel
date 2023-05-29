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
    private int recommendationTouristSpotNumber;
    private String imageUrl; 
    private String title;
    private String content;
    private String address;
    private String useTime;
    private String information;
    private String writeDate;
    private String region;

    public RecommendationTouristSpotEntity(String recommendTouristRegion, PostRecommendationTouristSpotRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String writeDate = simpleDateFormat.format(now);

        this.imageUrl = dto.getRecommendTouristImageUrl();
        this.title = dto.getRecommendTouristSpotTitle();
        this.content = dto.getRecommendTouristContent();
        this.address = dto.getRecommendTouristAddress();
        this.useTime = dto.getRecommendTouristUseTime();
        this.information = dto.getRecommendTouristInformation();
        this.writeDate = writeDate;
        this.region = recommendTouristRegion;
    }
}
