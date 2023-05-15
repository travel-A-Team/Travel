package com.travelproject.travelproject.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.admin.TouristSpotEntity;

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
}
