package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.resultSet.RegionResultSet;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    public RegionEntity findByName(String regionName);

    @Query(
        value = 
        "SELECT " +
        "name AS regionName " +
        "FROM Region;",
        nativeQuery = true
    )
    public List<RegionResultSet> getRegionList();

    public RegionEntity findByNameContains(String regionName);

}
