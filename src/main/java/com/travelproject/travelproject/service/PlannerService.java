package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.plannerBoard.PatchPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.request.plannerBoard.PostPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerListResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerTouristSpotResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerWriteListResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface PlannerService {
    // 플래너 작성
    public ResponseEntity<ResponseDto> postPlannerBoard(UserToken userToken, PostPlannerBoardRequestDto dto);

    // 플래너 목록 조회
    public ResponseEntity<? super GetPlannerListResponseDto> getPlannerBoardList();

    // 플래너 작성 시 지역 이름 리스트, 여행지 리스트 불러오기
    public ResponseEntity<? super GetPlannerWriteListResponseDto> getWritePlannerBoardList();

    // 플래너 작성 시 여행지 지역 검색
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchRegionNameSpotList(String writeRegion);

    // 플래너 작성 시 여행지 이름 검색
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchTouristSpotNameList(String writeTouristSpotName);

    // 플래너 작성 시 여행지 지역, 이름 검색
    public ResponseEntity<? super GetPlannerTouristSpotResponseDto> getSearchTouristSpotList(String writeRegion, String writeTouristSpotName);

    // 특정 플래너 상세 조회
    public ResponseEntity<? super GetPlannerResponseDto> getPlannerBoard(Integer plannerNumber);

    // 특정 플래너 수정
    public ResponseEntity<ResponseDto> patchPlannerBoard(UserToken userToken, PatchPlannerBoardRequestDto dto);

    // 특정 플래너 삭제
    public ResponseEntity<ResponseDto> deletePlannerBoard(UserToken userToken, Integer plannerBplannerNumberoardNumber);

}
