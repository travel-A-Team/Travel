package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.QuestionBoardEntity;
import com.travelproject.travelproject.entity.listEntity.QuestionListResultSet;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionBoardEntity, Integer>{
    
    public QuestionBoardEntity findByQuestionBoardNumber(int questionBoardNumber);
    
    @Query(
        value = 
        "SELECT " +
        "Q.question_board_number AS questionBoardNumber," +
        "Q.question_board_writer_email AS questionBoardWriterEmail," +
        "Q.question_board_title AS questionBoardTitle," +
        "Q.question_board_date AS questionBoardDate," +
        "Q.answer_status AS answerStatus " +
        "FROM Questionboard Q " +
        "GROUP BY questionBoardNumber " +
        "ORDER BY questionBoardDate DESC, questionBoardNumber DESC;",
        nativeQuery = true
    )
    public List<QuestionListResultSet> getQuestionList();

}
