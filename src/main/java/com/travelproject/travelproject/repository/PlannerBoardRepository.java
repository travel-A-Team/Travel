package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.listEntity.PlannerBoardResultSet;
import com.travelproject.travelproject.entity.planner.PlannerEntity;

@Repository
public interface PlannerBoardRepository extends JpaRepository<PlannerEntity, Integer> {

    public PlannerEntity findByPlannerNumber(int plannerNumber);

    @Query(value = "SELECT " +
            "P.planner_number AS plannerNumber," +
            "P.planner_title AS plannerTitle," +
            "P.planner_tour_route AS plannerTourRoute," +
            "P.planner_money AS plannerMoney," +
            "P.planner_image_url AS plannerImageUrl " +
            "FROM Planner P " +
            "ORDER BY P.planner_number DESC", nativeQuery = true)

    public List<PlannerBoardResultSet> getPlannerBoardList();

    @Transactional
    void deleteByPlannerNumber(int plannerNumber);

}
