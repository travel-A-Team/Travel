package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.adminNoticeBoard.PatchNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.request.adminNoticeBoard.PostNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.adminNoticeBoard.GetNoticeBoardListResponseDto;
import com.travelproject.travelproject.dto.response.adminNoticeBoard.GetNoticeBoardResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface NoticeBoardService {
    public ResponseEntity<ResponseDto> postNoticBoard(PostNoticeBoardRequestDto dto);
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticBoardList(UserToken userToken);
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticBoard(UserToken userToken, Integer noticeBoardNumber);
    public ResponseEntity<ResponseDto> patchNoticBoard(UserToken userToken, PatchNoticeBoardRequestDto dto);
    public ResponseEntity<ResponseDto> deleteNoticBoard(UserToken userToken, Integer noticeBoardNumber);
}
