package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;
import com.travelproject.travelproject.dto.request.adminTouristSpot.PostTouristSpotRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface TouristSpotService {
    public ResponseEntity<ResponseDto> postTouristSpot(UserToken userToken, PostTouristSpotRequestDto dto);
}
