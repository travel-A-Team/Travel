package com.travelproject.travelproject.dto.response.adminNoticeBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.admin.NoticeBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetNoticeBoardListResponseDto extends ResponseDto{
    List<NoticeBoard> noticeBoardList;

    public GetNoticeBoardListResponseDto(List<NoticeBoardEntity> resultSetList) {
        super("SU", "Success");

        List<NoticeBoard> noticeBoardList = new ArrayList<NoticeBoard>();

        for(NoticeBoardEntity resultSet: resultSetList) {
            NoticeBoard noticeBoard = new NoticeBoard(resultSet);
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

    public NoticeBoard(NoticeBoardEntity resultSet) {
        this.noticeBoardNumber = resultSet.getNoticeBoardNumber();
        this.noticeBoardTitle = resultSet.getNoticeBoardTitle();
        this.noticeBoardWriteDate = resultSet.getNoticeBoardWriteDate();
    }
}

