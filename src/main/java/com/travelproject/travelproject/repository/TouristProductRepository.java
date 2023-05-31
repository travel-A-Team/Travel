package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.resultSet.ProductResultSet;
import com.travelproject.travelproject.entity.resultSet.TouristProductListResultSet;
import com.travelproject.travelproject.entity.resultSet.TouristProductResultSet;

@Repository
public interface TouristProductRepository extends JpaRepository<TouristProductEntity, Integer>{

    public TouristProductEntity findByProductNumber(int productNumber);
    
    
    @Query(
        value = 
        "SELECT " +
        "T.product_number AS productNumber," +
        "T.title AS productTitle," +
        "T.image_url AS productImageUrl," +
        "T.money AS productMoney," +
        "count(DISTINCT L.user_email) AS likeyCount " +
        "FROM Touristproduct T, User U, Likey L " +
        "WHERE T.product_number = L.product_number " +
        "AND L.user_email = U.email " +
        "GROUP BY T.product_number " +
        "ORDER BY likeyCount DESC " +
        "LIMIT 3;",
        nativeQuery = true  
    )
    public List<ProductResultSet> getProductTop3();
    
    @Query(
        value = 
        "SELECT " +
        "T.title AS productTitle," +
        "T.image_url AS productImageUrl," +
        "T.money AS productMoney," +
        "T.tour_route AS productTourRoute " +
        "FROM Touristproduct T " +
        "ORDER BY write_date DESC, product_number DESC ",
        nativeQuery = true
    )
    public List<ProductResultSet> getTourCourseList();

    @Query (
        value = 
        "SELECT " +
        "T.product_number AS productNumber," +
        "T.title AS productTitle," +
        "T.total_schedule AS productTotalSchedule," +
        "T.tour_route AS productTourRoute," +
        "T.money AS productMoney," +
        "count(L.user_email) AS likeCount " +
        "FROM Touristproduct AS T " +
        "LEFT JOIN Likey AS L " +
        "ON t.product_number = L.product_number " +
        "WHERE T.product_number = ? " +
        "GROUP BY T.product_number",
        nativeQuery = true
        )
    public TouristProductResultSet getTouristProduct(int productNumber);

    @Query(
        value = 
        "SELECT " +
        "T.product_number AS productNumber," +
        "T.image_url AS productImageUrl," +
        "T.title AS productTitle," +
        "T.total_schedule AS productTotalSchedule," +
        "T.tour_route AS productTourRoute," +
        "T.money AS productMoney," +
        "count(L.user_email) AS likeCount," +
        "T.write_date AS productWriteDate " +
        "FROM Touristproduct AS T " +
        "LEFT JOIN Likey AS L " +
        "ON T.product_number = L.product_number " +
        "GROUP BY T.product_number " +
        "ORDER BY T.write_date DESC, T.product_number DESC",
        nativeQuery = true
        )
    public List<TouristProductListResultSet> getTouristProductList();

    @Query(
        value = 
        "SELECT * " +
        "FROM Touristproduct " +
        "ORDER BY write_date DESC, product_number DESC",
        nativeQuery = true
        )
    public List<TouristProductEntity> getTouristProductListInAdminMainPage();

    @Query(
        value = 
        "SELECT * " +
        "FROM Touristproduct "  +
        "WHERE product_number = ?",
        nativeQuery = true
    )
    public TouristProductEntity getUserLikeProduct(int productNumber);
}

