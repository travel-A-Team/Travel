package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.primaryKey.LikeyPk;

@Repository
public interface LikeyRepository extends JpaRepository<LikeyEntity, LikeyPk>{
    
    List<LikeyEntity> findByLikeyProduct(int likeyProduct);

}