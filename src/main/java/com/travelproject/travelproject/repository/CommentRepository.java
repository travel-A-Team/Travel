package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    
    List<CommentEntity> findByQuestionBoardNumber(int questionBoardNumber);

    @Query(
        value = 
        "SELECT * " +
        "FROM Comment " +
        "WHERE question_board_number = ?;",
        nativeQuery = true
    )
    public CommentEntity CommentQuestionNumber(int questionBoardNumber);

    @Transactional
    void deleteByQuestionBoardNumber(int questionBoardNumber);
    
}
