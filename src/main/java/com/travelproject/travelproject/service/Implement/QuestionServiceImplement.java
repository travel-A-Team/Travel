package com.travelproject.travelproject.service.Implement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.questionBoard.PatchCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PatchQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionListResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionResponseDto;
import com.travelproject.travelproject.entity.CommentEntity;
import com.travelproject.travelproject.entity.QuestionBoardEntity;
import com.travelproject.travelproject.entity.UserEntity;
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

        try {
            //# 토큰 검증
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
            String questionBoardWriterEmail = userToken.getEmail();

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

            List<QuestionBoardEntity> resultSet = questionRepository.getQuestionList();

            List<String> localAnswerStatus = new ArrayList<>();

            for (int count = 0; count < resultSet.size(); count++) {
                Integer answerStatus = resultSet.get(count).getAnswerStatus();
                if (answerStatus == 0){
                    localAnswerStatus.add("답변 대기");
                } else {
                    localAnswerStatus.add("답변 완료"); 
                }
            }
            
            body = new GetQuestionListResponseDto(resultSet,localAnswerStatus);
            
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
        String localAnswerStatus;

        try {
            //# 존재하지 않는 문의사항 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

                Integer answerStatus = questionBoardEntity.getAnswerStatus();
                if (answerStatus == 0){
                    localAnswerStatus = "답변 대기";
                } else {
                    localAnswerStatus = "답변 완료"; 
                }

            List<CommentEntity> commentEntities = commentRepository.findByQuestionBoardNumber(questionBoardNumber);

            body = new GetQuestionResponseDto(questionBoardEntity, commentEntities, localAnswerStatus);

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
        
        Integer questionBoardNumber = dto.getQuestionBoardNumber();
        String questionTitle = dto.getQuestionBoardTitle();
        String questionContent = dto.getQuestionBoardContent();

        try {
            //# 토큰 검증
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
            String questionBoardWriterEmail = userToken.getEmail();

            //# 요청 매개변수 검증 실패
            if (questionBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;

            //# 존재하지 않는 문의사항 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;
            
            //# 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(questionBoardWriterEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            //# 권한 없음
            boolean equalWriter = questionBoardEntity.getWriterEmail().equals(questionBoardWriterEmail);
            if (!equalWriter) return ResponseMessage.NO_PERMISSIONS;

            questionBoardEntity.setTitle(questionTitle);
            questionBoardEntity.setContent(questionContent);

            questionRepository.save(questionBoardEntity);

        } catch (Exception exception) {
            //# 데이터베이스 오류
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //! 문의사항 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteQuestionBoard(UserToken userToken, Integer questionBoardNumber) {

        try {
            //# 토큰 검증
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;
            String questionBoardWriterEmail = userToken.getEmail();

            //# 존재하지 않는 문의사항 번호
            QuestionBoardEntity questionBoardEntity = questionRepository.findByQuestionBoardNumber(questionBoardNumber);
            if (questionBoardEntity == null) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

            //# 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByEmail(questionBoardWriterEmail);
            if (!existedUserEmail) return ResponseMessage.NOT_EXIST_USER_EMAIL;

            //# 권한 없음
            boolean equalWriter = questionBoardEntity.getWriterEmail().equals(questionBoardWriterEmail);
            if (!equalWriter) return ResponseMessage.NO_PERMISSIONS;

            commentRepository.deleteByQuestionBoardNumber(questionBoardNumber);
            questionRepository.delete(questionBoardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //! 댓글 작성
    @Override
    public ResponseEntity<ResponseDto> postCommentBoard(UserToken userToken, PostCommentBoardRequestDto dto) {
        
        try {
            //# 관리자 토큰 검증 실패
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

            //# 관리자 이메일 검증
            String commentBoardAdminEmail = userToken.getEmail();
            if (commentBoardAdminEmail == null) return ResponseMessage.NOT_EXIST_USER_EMAIL;
            UserEntity userEntity = userRepository.findByEmail(commentBoardAdminEmail); 

            //# 관리자인지 확인(권한 없음)
            if (!userToken.getRole().equals("admin")) return ResponseMessage.NO_PERMISSIONS;

            //# 요청 매개변수 검증 실패
            if (dto == null) return ResponseMessage.VAILDATION_FAILED;

            // String adminName = userEntity.getName();
            // CommentEntity commentEntity = new CommentEntity(adminName, dto);
            //댓글을 작성한 사용자의 이름이라는 데이터베이스에 존재하는 컬럼이 필요가 없을 거 같아서 삭제했습니다.
            CommentEntity commentEntity = new CommentEntity(dto);
            commentRepository.save(commentEntity);
            
        } catch (Exception exception) {
            //# DB오류
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //! 댓글 수정
    @Override
    public ResponseEntity<ResponseDto> patchCommentBoard(UserToken userToken, PatchCommentBoardRequestDto dto) {
        
        Integer questionCommentNumber = dto.getQuestionBoardNumber();
        String commentContent = dto.getCommentContent();

        try {
            //# 관리자 토큰 검증 실패
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN; 

            //# 관리자인지 확인(권한 없음)
            if (!userToken.getRole().equals("admin")) return ResponseMessage.NO_PERMISSIONS;

            //# 요청 매개변수 검증 실패
            if (questionCommentNumber == null || commentContent == null) return ResponseMessage.VAILDATION_FAILED;

            //# 존재하지 않는 문의사항 번호
            boolean existedAdminQuestionNumber = questionRepository.existsByQuestionBoardNumber(questionCommentNumber);
            if (!existedAdminQuestionNumber) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String commentPatchDate = simpleDateFormat.format(now);

            CommentEntity commentEntity = commentRepository.CommentQuestionNumber(questionCommentNumber);
            
            commentEntity.setContent(commentContent);
            commentEntity.setWriteDatetime(commentPatchDate);

            commentRepository.save(commentEntity);

        } catch (Exception exception) {
            //# DB오류
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //! 댓글 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteCommentBoard(UserToken userToken, Integer questionBoardNumber) {
        
        try {
            //# 관리자 토큰 검증 실패
            if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN; 

            //# 관리자인지 확인(권한 없음)
            if (!userToken.getRole().equals("admin")) return ResponseMessage.NO_PERMISSIONS;

            //# 요청 매개변수 검증 실패
            if (questionBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;

            //# 존재하지 않는 문의사항 번호
            boolean existedAdminQuestionNumber = questionRepository.existsByQuestionBoardNumber(questionBoardNumber);
            if (!existedAdminQuestionNumber) return ResponseMessage.NOT_EXIST_QUESTION_BOARD_NUMBER;

            commentRepository.deleteByQuestionBoardNumber(questionBoardNumber);

        } catch (Exception exception) {
            //# DB오류
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
}
