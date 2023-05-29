package com.travelproject.travelproject.dto.response.questionBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.QuestionBoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetQuestionListResponseDto extends ResponseDto {

    private List<QuestionBoardList> questionList;

    public GetQuestionListResponseDto(List<QuestionBoardEntity> resultSet, List<String> localAnswerStatus) {
        super("SU", "Success");

        List<QuestionBoardList> questionList = new ArrayList<>();

        for (int count = 0; count < resultSet.size(); count++) {
            String status = localAnswerStatus.get(count);
            QuestionBoardEntity entity = resultSet.get(count);
            QuestionBoardList questionBoardList = new QuestionBoardList(entity, status);
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
    private String questionBoardDate;
    private String isAnswerStatus;

    public QuestionBoardList(QuestionBoardEntity questionBoardEntity, String localAnswerStatus) {
        this.questionBoardNumber=questionBoardEntity.getQuestionBoardNumber();
        this.questionBoardTitle=questionBoardEntity.getTitle();
        this.questionBoardWriterEmail=questionBoardEntity.getWriterEmail();
        this.questionBoardDate=questionBoardEntity.getWriteDate();
        this.isAnswerStatus=localAnswerStatus;
    }
}
