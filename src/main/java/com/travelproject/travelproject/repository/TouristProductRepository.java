package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.listEntity.ProductResultSet;

@Repository
public interface TouristProductRepository extends JpaRepository<TouristProductEntity, Integer>{
    
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

}
