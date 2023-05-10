package com.travelproject.travelproject.common.constant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.travelproject.travelproject.dto.response.ResponseDto;


public interface ResponseMessage {
    public static final ResponseEntity<ResponseDto> SUCCESS 
    = ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("SU", "SUCCESS"));

    public static final ResponseEntity<ResponseDto> VAILDATION_FAILED 
    = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto("VF", "Request Parameter Validation Failed"));

    public static final ResponseEntity<ResponseDto> NOT_EXIST_BOARD_NUMBER 
    = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto("NB", "Non-Existent Board Number"));

    public static final ResponseEntity<ResponseDto> SIGN_IN_FAILED
    = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto("SF", "Sign In Failed"));

    public static final ResponseEntity<ResponseDto> NOT_EXIST_USER_EMAIL 
    = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto("NU", "Non-Existent User Email"));

    public static final ResponseEntity<ResponseDto> NO_PERMISSIONS 
    = ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseDto("NP", "No Permissions"));

    public static final ResponseEntity<ResponseDto> DATABASE_ERROR 
    = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DE", "Database Error"));
    
}
