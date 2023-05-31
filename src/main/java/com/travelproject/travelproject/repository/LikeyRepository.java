package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.LikeyEntity;
import com.travelproject.travelproject.entity.primaryKey.LikeyPk;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;

@Repository
public interface LikeyRepository extends JpaRepository<LikeyEntity, LikeyPk>{
    
    List<LikeyEntity> findByProductNumber(int likeyProduct);

    @Query(
        value = 
        "SELECT " +
        "count(DISTINCT L.user_email) AS likeyCount " +
        "FROM Likey L;",
        nativeQuery = true
    )
    public ProductResultSet getLikeyCount();

    @Query(
        value =
        "SELECT * " +
        "FROM Likey " +
        "WHERE product_number = ? ",
        nativeQuery = true
    )
    public LikeyEntity getLikey(int productNumber);
    
    public List<LikeyEntity> findByUserEmail(String userEmail);

}