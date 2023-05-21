package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelproject.travelproject.entity.RegionEntity;
import com.travelproject.travelproject.entity.listEntity.RegionResultSet;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    public RegionEntity findByRegionName(String regionName);

    @Query(
        value = 
        "SELECT " +
        "R.region_name AS regionName " +
        "FROM Region R;",
        nativeQuery = true
    )
    public List<RegionResultSet> getRegionList();

    public RegionEntity findByRegionNameContains(String regionName);

}
