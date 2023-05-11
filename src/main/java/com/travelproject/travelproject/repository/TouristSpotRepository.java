package com.travelproject.travelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.admin.TouristSpotEntity;

@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpotEntity, Integer> {
    public boolean existsByWriteTouristSpotAddress(String writeTouristSpotAddress);
}
