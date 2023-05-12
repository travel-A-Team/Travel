package com.travelproject.travelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelproject.travelproject.entity.RegionEntity;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    public RegionEntity findByRegionNameContains(String regionName);
}
