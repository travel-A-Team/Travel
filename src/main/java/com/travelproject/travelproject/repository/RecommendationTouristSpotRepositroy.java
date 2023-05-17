package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.RecommendationTouristSpotEntity;
import com.travelproject.travelproject.entity.listEntity.RecommendResultSet;

@Repository
public interface RecommendationTouristSpotRepositroy extends JpaRepository<RecommendationTouristSpotEntity, Integer>{
    
    // 추천 관련 쿼리문 적어야 됨
    @Query(
        value = 
        "SELECT " +
        "R.tourist_spot_title AS touristSpotTitle," +
        "R.recommend_tourist_spot_image_url AS recommendTouristSpotImageUrl," +
        "R.content " +
        "FROM Recommendationtouristspot R " +
        "ORDER BY R.tourist_spot_number DESC " + 
        "LIMIT 3;",
        nativeQuery = true
    )
    
    public List<RecommendResultSet> getRecommendList3();

    public boolean existsByTouristSpotAddress(String recommendTouristAddress);

    public RecommendationTouristSpotEntity findByTouristSpotNumber(int recommendTouristNumber);

    @Query(
        value = 
        "SELECT " +
        "tourist_spot_number,"  +
        "recommend_tourist_spot_image_url,"  +
        "tourist_spot_title,"  +
        "content,"  +
        "tourist_spot_address,"  +
        "tourist_spot_use_time,"  +
        "tourist_spot_information,"  +
        "recommend_tourist_spot_write_date,"  +
        "recommend_tourist_region "  +
        "FROM Recommendationtouristspot " +
        "ORDER BY recommend_tourist_spot_write_date DESC, tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<RecommendationTouristSpotEntity> getRecommendationTouristSpotList();
}
