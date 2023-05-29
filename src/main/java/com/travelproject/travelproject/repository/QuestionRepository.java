package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.QuestionBoardEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionBoardEntity, Integer>{
    
    public QuestionBoardEntity findByQuestionBoardNumber(int questionBoardNumber);

        @Query(
        value = 
        "SELECT * " +
        "FROM Questionboard " +
        "GROUP BY question_board_number " +
        "ORDER BY write_date DESC, question_board_number DESC;",
        nativeQuery = true
    )
    public List<QuestionBoardEntity> getQuestionList();

    public boolean existsByQuestionBoardNumber(int questionBoardNumber);

}
