package com.travelproject.travelproject.dto.response.noticeBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetNoticeListResponseDto extends ResponseDto {
    //! 목록 조회 Dto
    private List<NoticeBoardList> noticeBoardList;
    
    public GetNoticeListResponseDto(List<NoticeBoardEntity> noticeBoardEntities) {
        super("SU", "SUCCESS");

        List<NoticeBoardList> noticeBoardList = new ArrayList<>();

        for(NoticeBoardEntity noticeBoardEntity: noticeBoardEntities) {
            NoticeBoardList noticeBoard = new NoticeBoardList(noticeBoardEntity);
            noticeBoardList.add(noticeBoard);
        }

        this.noticeBoardList=noticeBoardList;
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class NoticeBoardList {
    private int noticeBoardNumber;
    private String noticeBoardTitle;
    private String noticeBoardDate;

    public NoticeBoardList(NoticeBoardEntity noticeBoardEntity) {
        this.noticeBoardNumber=noticeBoardEntity.getNoticeBoardNumber();
        this.noticeBoardTitle=noticeBoardEntity.getTitle();
        this.noticeBoardDate=noticeBoardEntity.getWriteDate();
    }
}
