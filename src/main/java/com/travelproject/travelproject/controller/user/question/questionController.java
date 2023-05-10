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
import com.travelproject.travelproject.dto.request.questionBoard.PatchQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionListResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionResponseDto;
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
        @Valid @RequestBody PostQuestionBoardRequestDto requsetBody
    ) {
        ResponseEntity<ResponseDto> response = questionService.postQuestionBoard(requsetBody);
        return response;
    }

    //# 문의사항 목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetQuestionListResponseDto> getQuestionBoardList() {
        ResponseEntity<? super GetQuestionListResponseDto> response =
            questionService.getQuestionBoardList();
        return response;
    }

    //# 특정 문의사항 상세조회
    @GetMapping("/{questionBoardNumber}")
    public ResponseEntity<? super GetQuestionResponseDto> getQuestionBoard(
        @PathVariable("questionBoardNumber") Integer questionBoardNumber
    ) {
        ResponseEntity<? super GetQuestionResponseDto> response =
            questionService.getQuestionBoard();
        return response;
    }

    //# 특정 문의사항 수정
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchQuestionBoard(
        @Valid @RequestBody PatchQuestionBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response =
            questionService.patchQuestionBoard(requestBody);
        return response;
    }

    //# 특정 문의사항 삭제
    @DeleteMapping("/{questionBoardNumber}")
    public ResponseEntity<ResponseDto> deleteQuestionBoard(
        @AuthenticationPrincipal String questionBoardWriterEmail,
        @PathVariable("questionBoardNumber") Integer questionBoardNumber
    ) {
        ResponseEntity<ResponseDto> response =
            questionService.deleteQuestionBoard(questionBoardWriterEmail, questionBoardNumber);
        return response;
    }

}
