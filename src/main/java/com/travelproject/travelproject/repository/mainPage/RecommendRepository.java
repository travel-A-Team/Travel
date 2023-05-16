package com.travelproject.travelproject.repository.mainPage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.listEntity.RecommendResultSet;
import com.travelproject.travelproject.entity.mainPage.RecommendEntity;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Integer>{
    
    // 추천 관련 쿼리문 적어야 됨
    @Query(
        value = 
        "SELECT " +
        "R.tourist_spot_name AS touristSpotName," +
        "R.recommend_tourist_spot_image_url AS recommendTouristSpotImageUrl," +
        "R.content " +
        "FROM Recommendationtouristspot R " +
        "ORDER BY R.tourist_spot_number DESC " + 
        "LIMIT 3;",
        nativeQuery = true
    )
    
    public List<RecommendResultSet> getRecommendList3();
}
