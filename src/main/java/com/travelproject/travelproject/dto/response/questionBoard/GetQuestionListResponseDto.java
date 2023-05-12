package com.travelproject.travelproject.dto.response.questionBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.questionBoardListEntity.QuestionListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetQuestionListResponseDto extends ResponseDto {

    private List<QuestionBoardList> questionList;

    public GetQuestionListResponseDto(List<QuestionListResultSet> resultSet) {
        super("SU", "Success");

        List<QuestionBoardList> questionList = new ArrayList<>();

        for (QuestionListResultSet result : resultSet) {
            QuestionBoardList questionBoardList = new QuestionBoardList(result);
            questionList.add(questionBoardList);
        }

        this.questionList=questionList;
    }
    
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class QuestionBoardList {
    private int questionBoardNumber;
    private String questionBoardTitle;
    private String questionBoardWriterEmail;
    private String questionWriterName;
    private String questionBoardDate;
    private boolean answerStatus;

    public QuestionBoardList(QuestionListResultSet resultSet) {
        this.questionBoardNumber=resultSet.getQuestionBoardNumber();
        this.questionBoardTitle=resultSet.getQuestionBoardTitle();
        this.questionBoardWriterEmail=resultSet.getQuestionBoardWriterEmail();
        this.questionWriterName=resultSet.getQuestionWriterName();
        this.questionBoardDate=resultSet.getQuestionBoardDate();
        this.answerStatus=resultSet.getIsAnswerStatus();
    }
}
