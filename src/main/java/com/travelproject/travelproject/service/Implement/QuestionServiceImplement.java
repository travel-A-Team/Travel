package com.travelproject.travelproject.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.questionBoard.PatchQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionListResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionResponseDto;
import com.travelproject.travelproject.entity.CommentEntity;
import com.travelproject.travelproject.entity.QuestionBoardEntity;
import com.travelproject.travelproject.entity.listEntity.QuestionListResultSet;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.CommentRepository;
import com.travelproject.travelproject.repository.QuestionRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.QuestionService;

@Service
public class QuestionServiceImplement implements QuestionService {

    //@ 유저, 문의사항, 댓글 Repository
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private CommentRepository commentRepository; 

    @Autowired
    public QuestionServiceImplement(
        UserRepository userRepository,
        QuestionRepository questionRepository,
        CommentRepository commentRepository
    ) {
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
        this.commentRepository=commentRepository;
    }

    //! 문의사항 작성
    @Override
    public ResponseEntity<ResponseDto> postQuestionBoard(UserToken userToken, PostQuestionBoardRequestDto dto) {

       String questionBoardWriterEmail = userToken.getEmail();
        
        try {
            //# 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(questionBoardWriterEmail);

            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            QuestionBoardEntity questionBoardEntity = new QuestionBoardEntity(questionBoardWriterEmail, dto);
            questionRepository.save(questionBoardEntity);

        } catch (Exception exception) {
            //# 데이터베이스 오류 반환
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        //# 성공 반환
        return ResponseMessage.SUCCESS;
    }

    //! 문의사항 목록 조회
    @Override
    public ResponseEntity<? super GetQuestionListResponseDto> getQuestionBoardList() {

        GetQuestionListResponseDto body = null;

        try {
            List<QuestionListResultSet> resultSet = questionRepository.getQuestionList();
            body = new GetQuestionListResponseDto(resultSet);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //! 문의사항 상세 조회
    @Override
    public ResponseEntity<? super GetQuestionResponseDto> getQuestionBoard(Integer questionBoardNumber) {
        
        GetQuestionResponseDto body = null;

        try {
            //# 존재하지 않는 게시물 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

            List<CommentEntity> commentEntities = commentRepository.findByQuestionBoardNumber(questionBoardNumber);

            body = new GetQuestionResponseDto(questionBoardEntity, commentEntities);
           
        } catch (Exception exception) {
            //# 데이터베이스 오류
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        //# 성공 반환
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //! 문의사항 수정
    @Override
    public ResponseEntity<ResponseDto> patchQuestionBoard(UserToken userToken, PatchQuestionBoardRequestDto dto) {
        
        String questionBoardWriterEmail = userToken.getEmail();

        Integer questionBoardNumber = dto.getQuestionBoardNumber();
        String questionTitle = dto.getQuestionBoardTitle();
        String questionContent = dto.getQuestionBoardContent();

        try {
            //# 요청 매개변수 검증 실패
            if (questionBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;

            //# 존재하지 않는 문의사항 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;
            
            //# 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(questionBoardWriterEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            //# 권한 없음
            boolean equalWriter = questionBoardEntity.getQuestionBoardWriterEmail().equals(questionBoardWriterEmail);
            if (!equalWriter) return ResponseMessage.NO_PERMISSIONS;

            questionBoardEntity.setQuestionBoardTitle(questionTitle);
            questionBoardEntity.setQuestionBoardContent(questionContent);

            questionRepository.save(questionBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //! 문의사항 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteQuestionBoard(UserToken userToken, Integer questionBoardNumber) {

        String questionBoardWriterEmail = userToken.getEmail();
        
        try {
            //# 1. 존재하지 않는 문의사항 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

            //# 2. 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(questionBoardWriterEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            //# 3. 권한 없음
            boolean equalWriter = questionBoardEntity.getQuestionBoardWriterEmail().equals(questionBoardWriterEmail);
            if (!equalWriter) return ResponseMessage.NO_PERMISSIONS;

            commentRepository.deleteByQuestionBoardNumber(questionBoardNumber);
            questionRepository.delete(questionBoardEntity);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
}
