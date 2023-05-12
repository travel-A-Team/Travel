package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.QuestionBoardEntity;
import com.travelproject.travelproject.entity.questionBoardListEntity.QuestionListResultSet;

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
        "Q.answer_status AS anwerStatus," +
        "U.name AS questionWriterName " +
        "FROM QuestionBoard Q, User U " +
        "WHERE Q.question_board_writer_email = U.email " +
        "GROUP BY Q.question_board_writer_email " +
        "ORDER BY questionBoardDate DESC;",
        nativeQuery = true
    )

    public List<QuestionListResultSet> getQuestionList();

}
