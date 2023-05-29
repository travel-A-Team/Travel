package com.travelproject.travelproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.DailyTravelDateEntity;
import com.travelproject.travelproject.entity.resultSet.DailyResultSet;

@Repository
public interface DailyTravelDateRepository  extends JpaRepository<DailyTravelDateEntity, Integer>{

    @Modifying
    @Transactional
    @Query(
        value = 
        "UPDATE " +
        "Dailytraveldate "+
        "SET "+
        "tourist_spot_image_url = ?," +
        "tourist_spot_name = ?," +
        "tourist_spot_address = ? " +
        "WHERE tourist_spot_number = ?", 
        nativeQuery = true
        )
    public int updateTouristSpot(String touristSpotImageUrl,  String touristSpotName,  String touristSpotAddress,  int touristSpotNumber);

    public boolean existsByTouristSpotNumber(int touristSpotNumber);

    @Query(
        value = 
        "SELECT " +
        "tourist_spot_number AS touristSpotNumber," +
        "daily_travel_number AS dailyTravelNumber," +
        "travel_date AS dailyTravelDate," +
        "sequence AS writeSequence," +
        "tourist_spot_image_url AS touristSpotImageUrl," +
        "tourist_spot_name AS writeTouristSpotName," +
        "tourist_spot_address AS touristSpotAddress " +
        "FROM Dailytraveldate " +
        "WHERE product_number = ? " +
        "ORDER BY sequence ASC",
        nativeQuery = true
        )
    public List<DailyResultSet> getDailyTravelDateList(int productNumber);

    public List<DailyTravelDateEntity> findByProductNumber(int productNumber);
    public DailyTravelDateEntity findByDailyTravelNumber(int dailyTravelNumber);
}
    

