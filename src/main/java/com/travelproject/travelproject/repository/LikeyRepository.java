package com.travelproject.travelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.primaryKey.LikeyPk;

public interface LikeyRepository extends JpaRepository<LikeyEntity, LikeyPk>{
    
}
