package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Touristproduct")
@Table(name = "Touristproduct") 
public class TouristProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productNumber;
    private String productImageUrl;
    private String productTitle;
    private String productTourRoute;
    private int productMoney;
    private String productTotalSchedule;
    private String productWriteDate;

    public TouristProductEntity (String productTourRoute, String productImageUrl, PostTouristProductRequestDto dto) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String productWriteDate = simpleDateFormat.format(date);

        this.productImageUrl = productImageUrl;
        this.productTitle = dto.getProductTitle();
        this.productTourRoute = productTourRoute;
        this.productMoney = dto.getProductMoney();
        this.productTotalSchedule = dto.getProductTotalSchedule();
        this.productWriteDate = productWriteDate;
    } 
}
