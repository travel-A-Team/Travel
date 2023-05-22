package com.travelproject.travelproject.dto.response.questionBoard;

import java.util.ArrayList;
import java.util.List;

import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.QuestionBoardEntity;
import com.travelproject.travelproject.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionResponseDto extends ResponseDto {
    private int questionBoardNumber;
    private String questionBoardTitle;
    private String questionBoardContent;
    private String questionBoardDate;
    private String questionAnswerStatus;
    private List<Comment> commentList;

    public GetQuestionResponseDto(QuestionBoardEntity questionBoardEntity, List<CommentEntity> commentEntities, String localAnswerStatus) {

        super("SU", "SUCCESS");

        this.questionBoardNumber = questionBoardEntity.getQuestionBoardNumber();
        this.questionBoardTitle = questionBoardEntity.getQuestionBoardTitle();
        this.questionBoardContent = questionBoardEntity.getQuestionBoardContent();
        this.questionBoardDate = questionBoardEntity.getQuestionBoardDate();
        this.questionAnswerStatus = localAnswerStatus;
        this.commentList = Comment.createList(commentEntities);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Comment {
    private int questionBoardNumber;
    private String commentEmail;
    private String commentContent;
    private String commentWriteTime;

    Comment(CommentEntity commentEntity) {
        this.questionBoardNumber = commentEntity.getQuestionBoardNumber();
        this.commentEmail = commentEntity.getWriteUserEmail();
        this.commentContent = commentEntity.getCommentContent();
        this.commentWriteTime = commentEntity.getCommentWriteTime();
    }

    static List<Comment> createList(List<CommentEntity> commentEntities) {
        List<Comment> commentList = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntities) {
            Comment comment = new Comment(commentEntity);
            commentList.add(comment);
        }
        return commentList;
    }
}
