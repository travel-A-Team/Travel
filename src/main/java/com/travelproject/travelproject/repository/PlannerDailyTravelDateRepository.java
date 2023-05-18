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
    List<PlannerDailyTravelDateEntity> findByPlannerPlannerNumber(int plannerPlannerNumber);

    public PlannerDailyTravelDateEntity findByPlannerDailyNumber(int plannerDailyNumber);

    public boolean existsByPlannerDailyNumber(int plannerDailyNumber);

    @Transactional
    void deleteByPlannerPlannerNumber(int plannerPlannerNumber);


    @Modifying
    @Transactional
    @Query(
        value = 
        "UPDATE " +
        "Plannerdailytraveldate SET " +
        "write_image_url = ?," +
        "write_tourist_spot_name = ?," +
        "write_planner_address = ? " +
        "WHERE tourist_spot_write_tourist_spot_number = ?",
        nativeQuery = true
        )
        public int updateTouristSpot(String writeImageUrl, String writeTouristSpotName, String writePlannerAddress, int touristSpotNumber);

        public boolean existsByTouristSpotWriteTouristSpotNumber(int touristSpotNumber);
}
