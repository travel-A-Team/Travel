package com.travelproject.travelproject.repository.mainPage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.listEntity.RecommendResultSet;
import com.travelproject.travelproject.entity.mainPage.RecommendEntity;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendEntity, Integer>{
    
    // 추천 관련 쿼리문 적어야 됨
    
    public List<RecommendResultSet> getRecommendList3();
}
