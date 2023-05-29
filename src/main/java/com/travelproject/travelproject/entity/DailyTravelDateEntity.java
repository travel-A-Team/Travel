package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.touristProduct.PatchTouristProductDaliyTravelDateRequestDto;
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
    private String travelDate;
    private int sequence;
    private String touristSpotImageUrl;
    private String touristSpotName;
    private String touristSpotAddress;


    public DailyTravelDateEntity(int productNumber, PostTouristProductDaliyTravelDateRequestDto dto) {
        this.touristSpotNumber = dto.getTouristSpotNumber();
        this.productNumber = productNumber;
        this.travelDate = dto.getDaliyTravelDate();
        this.touristSpotImageUrl = dto.getWriteImageUrl();
        this.touristSpotName = dto.getWriteTouristSpotName();
        this.touristSpotAddress = dto.getWriteProductAddress();
        this.sequence = dto.getWriteSequence();
    }
    
    public DailyTravelDateEntity(int productNumber, PatchTouristProductDaliyTravelDateRequestDto dto) {
        this.touristSpotNumber = dto.getTouristSpotNumber();
        this.productNumber = productNumber;
        this.travelDate = dto.getDailyTravelDate();
        this.touristSpotImageUrl = dto.getWriteImageUrl();
        this.touristSpotName = dto.getWriteTouristSpotName();
        this.touristSpotAddress = dto.getWriteProductAddress();
        this.sequence = dto.getWriteSequence();
    }
}
