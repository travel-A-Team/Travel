package com.travelproject.travelproject.dto.response.adminNoticeBoard;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.admin.NoticeBoardEntity;

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
        this.noticeBoardTitle = noticeBoardEntity.getNoticeBoardTitle();
        this.noticeBoardContent = noticeBoardEntity.getNoticeBoardContent();
        this.noticeBoardWriteDate = noticeBoardEntity.getNoticeBoardWriteDate();
    }
}