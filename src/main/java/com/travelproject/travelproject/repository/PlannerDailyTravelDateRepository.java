package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.PlannerDailyTravelDateEntity;

@Repository
public interface PlannerDailyTravelDateRepository extends JpaRepository<PlannerDailyTravelDateEntity, Integer>{
    List<PlannerDailyTravelDateEntity> findByPlannerNumber(int plannerNumber);

    public PlannerDailyTravelDateEntity findByPlannerDailyNumber(int plannerDailyNumber);

    public boolean existsByPlannerDailyNumber(int plannerDailyNumber);

    public boolean existsBySequence(int sequence);

    @Transactional
    void deleteByPlannerNumber(int plannerNumber);

    @Transactional
    void deleteByPlannerDailyNumber(int plannerDailyNumber);


    @Modifying
    @Transactional
    @Query(
        value = 
        "UPDATE " +
        "Plannerdailytraveldate SET " +
        "tourist_spot_image_url = ?," +
        "tourist_spot_name = ?," +
        "tourist_spot_address = ? " +
        "WHERE tourist_spot_number = ?",
        nativeQuery = true
        )
        public int updateTouristSpot(String touristSpotImageUrl, String touristSpotName, String touristSpotAddress, int touristSpotNumber);

        public boolean existsByTouristSpotNumber(int touristSpotNumber);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
        value = 
        "UPDATE " +
        "Plannerdailytraveldate SET " +
        "tourist_spot_number = ?," +
        "travel_date = ?," +
        "tourist_spot_image_url = ?," +
        "tourist_spot_name = ?," +
        "tourist_spot_address = ?," +
        "sequence = ? " +
        "WHERE planner_number = ? " +
        "AND planner_daily_number = ?",
        nativeQuery = true
        )
        public int updatePlannerDaily(int touristSpotNumber, String travelDate, String spotImageUrl, String spotName, String spotAddress, int sequence, int plannerNumber, int plannerDailyNuber);
    
}
