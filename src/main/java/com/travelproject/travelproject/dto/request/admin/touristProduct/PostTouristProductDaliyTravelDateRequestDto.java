package com.travelproject.travelproject.dto.request.admin.touristProduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PostTouristProductDaliyTravelDateRequestDto {
    @NotNull
    private Integer touristSpotNumber;
    @NotBlank
    private String daliyTravelDate;
    @NotNull
    private int sequence;
    @NotBlank
    private String writeImageUrl;
    @NotBlank
    private String writeTouristSpotName;
    @NotBlank
    private String writeProductAddress;
    @NotNull
    private Integer writeSequence;
}
