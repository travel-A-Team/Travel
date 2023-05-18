package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;
import com.travelproject.travelproject.entity.listEntity.TouristProductListResultSet;
import com.travelproject.travelproject.entity.listEntity.TouristProductResultSet;

@Repository
public interface TouristProductRepository extends JpaRepository<TouristProductEntity, Integer>{

    public TouristProductEntity findByProductNumber(int productNumber);
    
    // 상품 관련 쿼리문 적어야 됨
    // 여행지 코스는 필요없을거 같아서 일단 뺐음
    // 좋아요 관련된거 찾아야 됨
    @Query(
        value = 
        "SELECT " +
        "T.product_number AS productNumber," +
        "T.product_title AS productTitle," +
        "T.product_image_url AS productImageUrl," +
        "T.product_money AS productMoney," +
        "count(DISTINCT L.likey_user_email) AS likeyCount " +
        "FROM Touristproduct T, User U, Likey L " +
        "WHERE T.product_number = L.likey_product " +
        "AND L.likey_user_email = U.email " +
        "GROUP BY T.product_number " +
        "ORDER BY productNumber DESC " +
        "LIMIT 3;",
        nativeQuery = true  
    )
    public List<ProductResultSet> getProductTop3();
    
    @Query(
        value = 
        "SELECT " +
        "T.product_title AS productTitle," +
        "T.product_image_url AS productImageUrl," +
        "T.product_money AS productMoney," +
        "T.product_tour_route AS productTourRoute " +
        "FROM Touristproduct T;",
        nativeQuery = true
    )
    public List<ProductResultSet> getTourCourseList();

    @Query(
        value = 
        "SELECT " +
        "count(DISTINCT L.likey_user_email) AS likeyCount " +
        "FROM Likey L;",
        nativeQuery = true
    )
    public ProductResultSet getLikeyCount();




    @Query (
        value = 
        "SELECT " +
        "t.product_number AS productNumber," +
        "t.product_title AS productTitle," +
        "t.product_total_schedule AS productTotalSchedule," +
        "t.product_tour_route AS productTourRoute," +
        "t.product_money AS productMoney," +
        "count(l.likey_user_email) AS likeCount " +
        "FROM Touristproduct AS t " +
        "LEFT JOIN Likey AS l " +
        "ON t.product_number = l.likey_product " +
        "WHERE t.product_number = ? " +
        "GROUP BY t.product_number",
        nativeQuery = true
        )
    public TouristProductResultSet getTouristProduct(int productNumber);



    @Query(
        value = 
        "SELECT " +
        "t.product_number AS productNumber," +
        "t.product_image_url AS productImageUrl," +
        "t.product_title AS productTitle," +
        "t.product_total_schedule AS productTotalSchedule," +
        "t.product_tour_route AS productTourRoute," +
        "t.product_money AS productMoney," +
        "count(l.likey_user_email) AS likeCount," +
        "t.product_write_date AS productWriteDate " +
        "FROM Touristproduct AS t " +
        "LEFT JOIN Likey AS l " +
        "ON t.product_number = l.likey_product " +
        "GROUP BY t.product_number " +
        "ORDER BY t.product_write_date, t.product_number DESC",
        nativeQuery = true
        )
    public List<TouristProductListResultSet> getTouristProductList();
}
