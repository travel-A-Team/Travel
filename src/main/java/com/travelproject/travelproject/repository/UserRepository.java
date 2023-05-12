package com.travelproject.travelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelproject.travelproject.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    public boolean existsByEmail(String email);

}
