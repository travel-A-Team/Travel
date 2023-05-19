package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.touristProduct.PostTouristProductDaliyTravelDateRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Dailytraveldate")
@Table(name = "Dailytraveldate")
public class DailyTravelDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dailyTravelNumber;
    private int touristSpotNumber;
    private int productNumber;
    private String dailyTravelDate;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writeProductAddress;
    private int sequence;

    public DailyTravelDateEntity(int productNumber, PostTouristProductDaliyTravelDateRequestDto dto) {
        this.touristSpotNumber = dto.getTouristSpotNumber();
        this.productNumber = productNumber;
        this.dailyTravelDate = dto.getDaliyTravelDate();
        this.writeImageUrl = dto.getWriteImageUrl();
        this.writeTouristSpotName = dto.getWriteTouristSpotName();
        this.writeProductAddress = dto.getWriteProductAddress();
    }

}
