package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelproject.travelproject.entity.DailyTravelDateEntity;
import com.travelproject.travelproject.entity.listEntity.DailyResultSet;

public interface DailyTravelDateRepository extends JpaRepository<DailyTravelDateEntity, Integer>{
    
    public List<DailyResultSet> findByProductNumber(int productNumber);
    
}
