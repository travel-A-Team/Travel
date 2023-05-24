package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelproject.travelproject.entity.BannerEntity;

public interface BannerRepository extends JpaRepository<BannerEntity, Integer> {
    
    @Query(
        value = 
        "SELECT * " +
        "FROM Banner",
        nativeQuery = true
        )
    public List<BannerEntity> getBannerList();
    
}
