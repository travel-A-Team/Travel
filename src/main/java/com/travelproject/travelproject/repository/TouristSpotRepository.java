package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.TouristSpotEntity;
import com.travelproject.travelproject.entity.listEntity.PlannerWriteSpotResultSet;

@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpotEntity, Integer> {
    public boolean existsByWriteTouristSpotAddress(String writeTouristSpotAddress);
    public TouristSpotEntity findByTouristSpotNumber(int writeTouristSpotNumber);

    @Query(
        value = 
        "SELECT * " +
        "FROM TouristSpot " +
        "ORDER BY write_tourist_spot_date DESC, tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<TouristSpotEntity> getList();

    public List<TouristSpotEntity> findByWriteRegionContains (String region);

    public List<TouristSpotEntity> findByWriteTouristSpotNameContains(String touristSpotName);

    public List<TouristSpotEntity> findByWriteRegionAndWriteTouristSpotNameContains(String region, String touristSpotName);
    @Query(
        value = 
        "SELECT " +
        "write_image_url AS writeImageUrl," +
        "write_tourist_spot_name AS writeTouristSpotName," +
        "write_tourist_spot_address AS writeTouristSpotAddress," +
        "latitude," +
        "longitude " +
        "FROM TouristSpot " +
        "ORDER BY write_tourist_spot_date DESC, tourist_spot_number DESC",
        nativeQuery = true
    )
    public List<PlannerWriteSpotResultSet> getPlannerWriteSpotList();
}
