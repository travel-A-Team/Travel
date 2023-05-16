package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
