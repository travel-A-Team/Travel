package com.travelproject.travelproject.controller.user.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeListResponseDto;
import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeResponseDto;
import com.travelproject.travelproject.service.NoticeService;

@RestController
@RequestMapping(RequestPattern.NOTICE_API)
public class NoticeController {
    
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService=noticeService;
    }

    //# 공지사항 상세 조회
    @GetMapping("/{noticeBoardNumber}")
    public ResponseEntity<? super GetNoticeResponseDto> getNoticeBoard(
        @PathVariable("noticeBoardNumber") Integer noticeBoardNumber
    ) {
        ResponseEntity<? super GetNoticeResponseDto> response =
            noticeService.getNoticeBoard(noticeBoardNumber);
        return response;
    }

    //# 공지사항 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeBoardList() {
        ResponseEntity<? super GetNoticeListResponseDto> response =
            noticeService.getNoticeBoardList();
        return response;
    }
}
