package com.travelproject.travelproject.dto.response.admin.noticeBoard;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetNoticeBoardResponseDto extends ResponseDto {
    private int noticeBoardNumber;
    private String noticeBoardTitle;
    private String noticeBoardContent;
    private String noticeBoardWriteDate;

    public GetNoticeBoardResponseDto(NoticeBoardEntity noticeBoardEntity) {
        super("SU", "Success");

        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.noticeBoardTitle = noticeBoardEntity.getTitle();
        this.noticeBoardContent = noticeBoardEntity.getContent();
        this.noticeBoardWriteDate = noticeBoardEntity.getWriteDate();
    }
}
