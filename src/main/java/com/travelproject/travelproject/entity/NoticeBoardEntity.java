package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.admin.noticeBoard.PostNoticeBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Noticeboard")
@Table(name = "Noticeboard")
public class NoticeBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeBoardNumber;
    private String title;
    private String content;
    private String writeDate;

    public NoticeBoardEntity(PostNoticeBoardRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String noticeBoardWriteDate = simpleDateFormat.format(now);

        this.title = dto.getNoticeBoardTitle();
        this.content = dto.getNoticeBoardContent();
        this.writeDate = noticeBoardWriteDate;

    }

}
