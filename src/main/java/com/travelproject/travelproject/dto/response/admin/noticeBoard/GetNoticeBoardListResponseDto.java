package com.travelproject.travelproject.dto.response.admin.noticeBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetNoticeBoardListResponseDto extends ResponseDto{
    List<NoticeBoard> noticeBoardList;

    public GetNoticeBoardListResponseDto(List<NoticeBoardEntity> noticeBoardEntities) {
        super("SU", "Success");

        List<NoticeBoard> noticeBoardList = new ArrayList<NoticeBoard>();

        for(NoticeBoardEntity noticeBoardEntity: noticeBoardEntities) {
            NoticeBoard noticeBoard = new NoticeBoard(noticeBoardEntity);
            noticeBoardList.add(noticeBoard);
        }
        
        this.noticeBoardList = noticeBoardList;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class NoticeBoard {
    private int noticeBoardNumber;
    private String noticeBoardTitle;
    private String noticeBoardWriteDate;

    public NoticeBoard(NoticeBoardEntity noticeBoardEntity) {
        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.noticeBoardTitle = noticeBoardEntity.getTitle();
        this.noticeBoardWriteDate = noticeBoardEntity.getWriteDate();
    }
}

