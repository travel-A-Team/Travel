package com.travelproject.travelproject.controller.admin;

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
import com.travelproject.travelproject.dto.request.admin.noticeBoard.PatchNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.request.admin.noticeBoard.PostNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.noticeBoard.GetNoticeBoardListResponseDto;
import com.travelproject.travelproject.dto.response.admin.noticeBoard.GetNoticeBoardResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.NoticeBoardService;


@RestController
@RequestMapping(RequestPattern.ADMIN_NOTICE_BOARD_API)
public class NoticeBoardController {
    
    private NoticeBoardService adminNoticeBoardService;
    private final String POST_NOTICE_BOARD = "";
    private final String GET_NOTICE_BOARD_LIST="list";
    private final String GET_NOTICE_BOARD ="/{noticeBoardNumber}";
    private final String PATCH_NOTICE_BOARD = "";
    private final String DELETE_NOTICE_BOARD="/{noticeBoardNumber}";
    @Autowired
    public NoticeBoardController(NoticeBoardService adminNoticeBoardService) {
        this.adminNoticeBoardService = adminNoticeBoardService;
    }


    //* 공지사항 게시물 작성
    @PostMapping(POST_NOTICE_BOARD)
    public ResponseEntity<ResponseDto> postNoticeBoard(
        @AuthenticationPrincipal UserToken userToken,
       @Valid @RequestBody PostNoticeBoardRequestDto request
    ) {
        ResponseEntity<ResponseDto> response = adminNoticeBoardService.postNoticBoard(userToken, request);
        return response;
    }

    //* 공지사항 게시판 목록 조회
    @GetMapping(GET_NOTICE_BOARD_LIST)
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList(
        @AuthenticationPrincipal UserToken userToken
    ) {
        ResponseEntity<? super GetNoticeBoardListResponseDto> response = adminNoticeBoardService.getNoticBoardList(userToken);
        return response;
    }

    //* 특정 공지사항 게시물 조회
    @GetMapping(GET_NOTICE_BOARD)
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard 
    (
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("noticeBoardNumber") Integer noticeBoardNumber
    ) {
        ResponseEntity<? super GetNoticeBoardResponseDto> response = adminNoticeBoardService.getNoticBoard(userToken, noticeBoardNumber);
        return response;
    }

    //* 특정 공지사항 게시물 수정
   @PatchMapping(PATCH_NOTICE_BOARD)
   public ResponseEntity<ResponseDto> patchNoticeBoardNumber(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PatchNoticeBoardRequestDto request
   ) {
        ResponseEntity<ResponseDto> response = adminNoticeBoardService.patchNoticBoard(userToken, request);
        return response;
   }    

   //* 특정 공지사항 게시물 삭제
   @DeleteMapping(DELETE_NOTICE_BOARD)
   public ResponseEntity<ResponseDto> deleteNoticeBoard(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("noticeBoardNumber") Integer noticeBoardNumber
   ) {
        ResponseEntity<ResponseDto> response = adminNoticeBoardService.deleteNoticBoard(userToken, noticeBoardNumber);
        return response;
   }

}
