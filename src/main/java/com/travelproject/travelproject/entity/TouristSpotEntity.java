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
    private String touristSpotAddress;
    private String imageUrl;
    private String touristSpotName;
    private String region;
    private double latitude;
    private double longitude;
    private String writeDate;

    public TouristSpotEntity(String writeRegion, PostTouristSpotRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String writeTouristSpotDate = simpleDateFormat.format(now);

        this.touristSpotAddress = dto.getWriteTouristSpotAddress();
        this.imageUrl = dto.getWriteImageUrl();
        this.touristSpotName = dto.getWriteTouristSpotName();
        this.region = writeRegion;
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.writeDate = writeTouristSpotDate;
    }


    
}
