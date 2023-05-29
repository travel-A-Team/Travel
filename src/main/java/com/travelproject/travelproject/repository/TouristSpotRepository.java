package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.entity.resultSet.PlannerWriteSpotResultSet;

@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpotEntity, Integer> {
    public boolean existsByTouristSpotAddress(String writeTouristSpotAddress);
    public TouristSpotEntity findByTouristSpotNumber(int writeTouristSpotNumber);

    @Query(
        value = 
        "SELECT * " +
        "FROM TouristSpot " +
        "ORDER BY write_date DESC, tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<TouristSpotEntity> getList();

    public List<TouristSpotEntity> findByRegionContains (String region);

    public List<TouristSpotEntity> findByTouristSpotNameContains(String touristSpotName);

    public List<TouristSpotEntity> findByRegionAndTouristSpotNameContains(String region, String touristSpotName);

    @Query(
        value = 
        "SELECT " +
        "image_url AS writeImageUrl," +
        "tourist_spot_name AS writeTouristSpotName," +
        "tourist_spot_address AS writeTouristSpotAddress," +
        "latitude," +
        "longitude " +
        "FROM TouristSpot " +
        "ORDER BY write_date DESC, tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<PlannerWriteSpotResultSet> getPlannerWriteSpotList();
}
