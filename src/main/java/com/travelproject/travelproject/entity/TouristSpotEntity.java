package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.touristSpot.PostTouristSpotRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Touristspot")
@Table(name = "Touristspot")
public class TouristSpotEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int touristSpotNumber;
    private String writeTouristSpotAddress;
    private String writeImageUrl;
    private String writeTouristSpotName;
    private String writeRegion;
    private double latitude;
    private double longitude;
    private String writeTouristSpotDate;

    public TouristSpotEntity(String writeRegion, PostTouristSpotRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String writeTouristSpotDate = simpleDateFormat.format(now);

        this.writeTouristSpotAddress = dto.getWriteTouristSpotAddress();
        this.writeImageUrl = dto.getWriteImageUrl();
        this.writeTouristSpotName = dto.getWriteTouristSpotName();
        this.writeRegion = writeRegion;
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.writeTouristSpotDate = writeTouristSpotDate;
    }


    
}
