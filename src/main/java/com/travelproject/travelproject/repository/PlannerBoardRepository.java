package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.PlannerEntity;
import com.travelproject.travelproject.entity.resultSet.PlannerBoardResultSet;

@Repository
public interface PlannerBoardRepository extends JpaRepository<PlannerEntity, Integer> {

    public PlannerEntity findByPlannerNumber(int plannerNumber);

    @Query(
        value = 
        "SELECT " +
        "P.planner_number AS plannerNumber," +
        "P.title AS plannerTitle," +
        "P.tour_route AS plannerTourRoute," +
        "P.money AS plannerMoney," +
        "P.image_url AS plannerImageUrl " +
        "FROM Planner P " +
        "ORDER BY P.planner_number DESC", 
        nativeQuery = true
    )
    public List<PlannerBoardResultSet> getPlannerBoardList();

    @Transactional
    void deleteByPlannerNumber(int plannerNumber);

}
