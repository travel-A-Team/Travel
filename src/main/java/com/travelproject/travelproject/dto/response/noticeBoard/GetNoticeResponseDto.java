package com.travelproject.travelproject.dto.response.noticeBoard;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNoticeResponseDto extends ResponseDto {

    // ! 상세조회 Dto
    private int noticeBoardNumber;
    private String noticeBoardTitle;
    private String noticeBoardContent;
    private String noticeBoardDate;

    public GetNoticeResponseDto(NoticeBoardEntity noticeBoardEntity) {
        super("SU", "SUCCESS");

        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.noticeBoardTitle = noticeBoardEntity.getTitle();
        this.noticeBoardContent = noticeBoardEntity.getContent();
        this.noticeBoardDate = noticeBoardEntity.getWriteDate();
        
    }
}

