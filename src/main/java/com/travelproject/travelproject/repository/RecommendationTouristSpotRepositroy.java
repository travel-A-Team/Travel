package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;
import com.travelproject.travelproject.entity.resultSet.RecommendResultSet;

@Repository
public interface RecommendationTouristSpotRepositroy extends JpaRepository<RecommendationTouristSpotEntity, Integer>{
    
    @Query(
        value = 
        "SELECT " +
        "R.title AS touristSpotTitle," +
        "R.image_url AS recommendTouristSpotImageUrl," +
        "R.content " +
        "FROM Recommendationtouristspot R " +
        "ORDER BY R.recommendation_tourist_spot_number DESC " + 
        "LIMIT 3;",
        nativeQuery = true
    )
    public List<RecommendResultSet> getRecommendList3();

    public boolean existsByAddress(String recommendTouristAddress);

    public RecommendationTouristSpotEntity findByRecommendationTouristSpotNumber(int recommendTouristNumber);

    @Query(
        value = 
        "SELECT * " +
        "FROM Recommendationtouristspot " +
        "ORDER BY write_date DESC, recommendation_tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<RecommendationTouristSpotEntity> getRecommendationTouristSpotList();

    public List<RecommendationTouristSpotEntity> findByRegionAndTitleContains(String writeRegion, String writeRecommendSpotName);
    
}
