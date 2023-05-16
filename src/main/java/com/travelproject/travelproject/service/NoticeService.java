package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeListResponseDto;
import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeResponseDto;

public interface NoticeService {
    
    //! 공지사항 상세 조회
    public ResponseEntity<? super GetNoticeResponseDto> getNoticeBoard(Integer noticeBoardNumber);

    //! 공지사항 목록 조회
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeBoardList();

}
