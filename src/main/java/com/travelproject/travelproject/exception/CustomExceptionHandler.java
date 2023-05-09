package com.travelproject.travelproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.response.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    //@ 예외처리에 대한 상황
    // @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> HttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return ResponseMessage.VAILDATION_FAILED;
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> HandlerMethodArgumentNotValidExceptio(MethodArgumentNotValidException exception) {
        return ResponseMessage.VAILDATION_FAILED;
    }

}
