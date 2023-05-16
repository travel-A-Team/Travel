package com.travelproject.travelproject.controller.user.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.plannerBoard.PatchPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.request.plannerBoard.PostPlannerBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerListResponseDto;
import com.travelproject.travelproject.dto.response.plannerBoard.GetPlannerResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.PlannerService;

@RestController
@RequestMapping(RequestPattern.PLANNER_API)
public class PlannerController {

    private PlannerService plannerService;

    @Autowired
    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    // 플래너 작성
    @PostMapping("/planner-form")
    public ResponseEntity<ResponseDto> postPlannerBoard(
        @AuthenticationPrincipal UserToken userToken,
            @Valid @RequestBody PostPlannerBoardRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = plannerService.postPlannerBoard(userToken, requestBody);
        return response;
    }

    // 플래너 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetPlannerListResponseDto> getPlannerBoardList() {
        ResponseEntity<? super GetPlannerListResponseDto> response = plannerService.getPlannerBoardList();
        return response;
    }

    // 플래너 작성 폼 불러오기

    // 특정 플래너 상세 조회
    @GetMapping("/{plannerNumber}")
    public ResponseEntity<? super GetPlannerResponseDto> getPlannerBoard(
            @PathVariable("plannerNumber") Integer plannerNumber) {
        ResponseEntity<? super GetPlannerResponseDto> response = plannerService.getPlannerBoard(plannerNumber);
        return response;
    }

    // 특정 플래너 수정
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchPlannerBoard(
        @AuthenticationPrincipal UserToken userToken,
            @Valid @RequestBody PatchPlannerBoardRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = plannerService.patchPlannerBoard(userToken, requestBody);
        return response;
    }

    // 특정 플래너 삭제
    @DeleteMapping("/{plannerNumber}")
    public ResponseEntity<ResponseDto> deletePlannerBoard(
        @AuthenticationPrincipal UserToken userToken,
            @PathVariable("plannerNumber") Integer plannerNumber) {
        ResponseEntity<ResponseDto> response = plannerService.deletePlannerBoard(userToken, plannerNumber);
        return response;
    }

}
