package com.travelproject.travelproject.controller.user.question;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.request.questionBoard.PatchCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PatchQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionListResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.QuestionService;

@RestController
@RequestMapping(RequestPattern.QUESTION_API)
public class QuestionController {

   private QuestionService questionService;

   @Autowired
   public QuestionController(QuestionService questionService) {
        this.questionService=questionService;
   }

    //# 문의사항 작성
    @PostMapping("/question-form")
    public ResponseEntity<ResponseDto> postQuestionBoard(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostQuestionBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = questionService.postQuestionBoard(userToken, requestBody);
        return response;
    }

    //# 문의사항 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetQuestionListResponseDto> getQuestionBoardList() {
        ResponseEntity<? super GetQuestionListResponseDto> response = questionService.getQuestionBoardList();
        return response;
    }

    //# 문의사항 상세조회
    @GetMapping("/{questionBoardNumber}")
    public ResponseEntity<? super GetQuestionResponseDto> getQuestionBoard(
        @PathVariable("questionBoardNumber") Integer questionBoardNumber
    ) {
        ResponseEntity<? super GetQuestionResponseDto> response =
            questionService.getQuestionBoard(questionBoardNumber);
        return response;
    }

    //# 문의사항 수정
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchQuestionBoard(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PatchQuestionBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response =
            questionService.patchQuestionBoard(userToken, requestBody);
        return response;
    }

    //# 문의사항 삭제
    @DeleteMapping("/{questionBoardNumber}")
    public ResponseEntity<ResponseDto> deleteQuestionBoard(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("questionBoardNumber") Integer questionBoardNumber
    ) {
        ResponseEntity<ResponseDto> response =
            questionService.deleteQuestionBoard(userToken, questionBoardNumber);
        return response;
    }

    //# 댓글 작성
    @PostMapping("/comment-form")
    public ResponseEntity<ResponseDto> postCommentBoard(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PostCommentBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = questionService.postCommentBoard(userToken, requestBody);
        return response;
    }

    //# 댓글 수정
    @PatchMapping("/comment-board") 
    public ResponseEntity<ResponseDto> patchCommentBoard(
        @AuthenticationPrincipal UserToken userToken,
        @Valid @RequestBody PatchCommentBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = questionService.patchCommentBoard(userToken, requestBody);
        return response;
    }
     
    //# 댓글 삭제
    @DeleteMapping("/comment-board/{questionBoardNumber}")
    public ResponseEntity<ResponseDto> deleteCommentBoard(
        @AuthenticationPrincipal UserToken userToken,
        @PathVariable("questionBoardNumber") Integer questionBoardNumber
    ) {
        ResponseEntity<ResponseDto> response = questionService.deleteCommentBoard(userToken, questionBoardNumber);
        return response;
    }
}
