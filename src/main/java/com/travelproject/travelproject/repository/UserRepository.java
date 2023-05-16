package com.travelproject.travelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public boolean existsByEmail(String email);

}
