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
    private String imageUrl;
    private String title;
    private String tourRoute;
    private int money;
    private String totalSchedule;
    private String writeDate;

    public TouristProductEntity (String productTourRoute, String productImageUrl, PostTouristProductRequestDto dto) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String productWriteDate = simpleDateFormat.format(date);

        this.imageUrl = productImageUrl;
        this.title = dto.getProductTitle();
        this.tourRoute = productTourRoute;
        this.money = dto.getProductMoney();
        this.totalSchedule = dto.getProductTotalSchedule();
        this.writeDate = productWriteDate;
    } 
}
