package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.questionBoard.PatchCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostCommentBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Comment")
@Table(name = "Comment")
public class CommentEntity {
    
    @Id
    private int questionBoardNumber;
    private String content;
    private String writeDatetime;

    public CommentEntity(PostCommentBoardRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String commentBoardDate = simpleDateFormat.format(now);

        this.questionBoardNumber=dto.getQuestionBoardNumber();
        this.content=dto.getCommentContent();
        this.writeDatetime=commentBoardDate;
    }
}
