package com.travelproject.travelproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.DailyTravelDateEntity;

@Repository
public interface DailyTravelDateRepository  extends JpaRepository<DailyTravelDateEntity, Integer>{

    @Modifying
    @Transactional
    @Query(
        value = 
        "UPDATE " +
        "Dailytraveldate "+
        "SET "+
        "write_image_url = ?," +
        "write_tourist_spot_name = ?," +
        "write_product_address = ? " +
        "WHERE tourist_spot_number = ?", 
        nativeQuery = true
        )
    public int updateTouristSpot(String writeImageUrl,  String writeTouristSpotName,  String writeProductAddress,  int touristSpotNumber);

    public boolean existsByTouristSpotNumber(int touristSpotNumber);

}
