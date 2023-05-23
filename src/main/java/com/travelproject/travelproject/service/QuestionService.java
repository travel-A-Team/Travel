package com.travelproject.travelproject.service;

import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.request.questionBoard.PatchCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PatchQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostCommentBoardRequestDto;
import com.travelproject.travelproject.dto.request.questionBoard.PostQuestionBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionListResponseDto;
import com.travelproject.travelproject.dto.response.questionBoard.GetQuestionResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface QuestionService {

    public ResponseEntity<ResponseDto> postQuestionBoard(UserToken userToken, PostQuestionBoardRequestDto dto);
    
    public ResponseEntity<? super GetQuestionListResponseDto> getQuestionBoardList();
    public ResponseEntity<? super GetQuestionResponseDto> getQuestionBoard(Integer questionBoardNumber);

    public ResponseEntity<ResponseDto> patchQuestionBoard(UserToken userToken, PatchQuestionBoardRequestDto dto);

    public ResponseEntity<ResponseDto> deleteQuestionBoard(UserToken userToken, Integer questionBoardNumber);

    public ResponseEntity<ResponseDto> postCommentBoard(UserToken userToken, PostCommentBoardRequestDto dto);

    public ResponseEntity<ResponseDto> patchCommentBoard(UserToken userToken, PatchCommentBoardRequestDto requestBody);

    public ResponseEntity<ResponseDto> deleteCommentBoard(UserToken userToken, Integer questionBoardNumber);
    
}
