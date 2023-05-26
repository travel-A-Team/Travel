package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public boolean existsByEmail(String email);

    public UserEntity findByEmail(String email);

    
    @Query (
        value = 
        "SELECT * " +
        "FROM User " +
        "ORDER BY register_date DESC",
         nativeQuery = true
    )
    public List<UserEntity> getUserList();

}
