package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeListResponseDto;
import com.travelproject.travelproject.dto.response.noticeBoard.GetNoticeResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;
import com.travelproject.travelproject.repository.NoticeBoardRepository;
import com.travelproject.travelproject.service.NoticeService;

@Service
public class NoticeServiceImplement implements NoticeService {

    private NoticeBoardRepository noticeRepository;

    @Autowired
    public NoticeServiceImplement(NoticeBoardRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    // ! 공지사항 목록 조회
    @Override
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeBoardList() {

        GetNoticeListResponseDto body = null;

        try {
            List<NoticeBoardEntity> resultSet = noticeRepository.getList();
            body = new GetNoticeListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // ! 공지사항 상세 조회
    @Override
    public ResponseEntity<? super GetNoticeResponseDto> getNoticeBoard(Integer noticeBoardNumber) {
        GetNoticeResponseDto body = null;

        try {
            // # 존재하지 않는 공지사항 번호
            NoticeBoardEntity noticeBoardEntity = noticeRepository.findByNoticeBoardNumber(noticeBoardNumber);
            if (noticeBoardEntity == null) return ResponseMessage.NOT_EXIST_NOTICE_BOARD_NUMBER;

            body = new GetNoticeResponseDto(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
