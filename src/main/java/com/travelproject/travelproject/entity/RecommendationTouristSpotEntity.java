package com.travelproject.travelproject.entity;

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
    private String touristSpotTitle;
    private String content;
    private String touristSpotAddress;
    private String touristSpotUseTime;
    private String touristSpotInformation;
    private String recommendTouristSpotWriteDate;
    private String recommendTouristRegion;

    public RecommendationTouristSpotEntity(PostRecommendationTouristSpotRequestDto dto) {
        this.recommendTouristSpotImageUrl = dto.getRecommendTouristImageUrl();
        this.touristSpotTitle = dto.getRecommendTouristSpotTitle();
    }
}
