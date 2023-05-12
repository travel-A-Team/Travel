package com.travelproject.travelproject.repository.mainPage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.listEntity.ProductResultSet;
import com.travelproject.travelproject.entity.mainPage.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
    
    // 상품 관련 쿼리문 적어야 됨

    public List<ProductResultSet> getProductTop3();

}
