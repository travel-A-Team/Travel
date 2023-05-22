package com.travelproject.travelproject.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Questionboard")
@Table(name = "Questionboard")
public class QuestionBoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionBoardNumber;
    private String questionBoardWriterEmail;
    private String questionBoardTitle;
    private String questionBoardContent;
    private String questionBoardDate;
    private Integer answerStatus;

    public QuestionBoardEntity(String questionBoardWriterEmail, PostQuestionBoardRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String questionBoardDate = simpleDateFormat.format(now);

        this.questionBoardWriterEmail=questionBoardWriterEmail;
        this.questionBoardTitle=dto.getQuestionBoardTitle();
        this.questionBoardContent=dto.getQuestionBoardContent();
        this.questionBoardDate=questionBoardDate;
    }
}
