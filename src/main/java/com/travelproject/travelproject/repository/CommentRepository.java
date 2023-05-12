package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.travelproject.travelproject.entity.CommentEntity;
import com.travelproject.travelproject.entity.primaryKey.CommentPk;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, CommentPk>{
    
    List<CommentEntity> findByQuestionBoardNumber(int questionBoardNumber);
    // 만약 commentNumber로 했을시

    @Transactional
    void deleteByQuestionBoardNumber(int questionBoardNumber);
    
}
