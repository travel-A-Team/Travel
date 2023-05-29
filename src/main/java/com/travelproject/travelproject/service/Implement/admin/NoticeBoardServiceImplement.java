package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.noticeBoard.PatchNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.request.admin.noticeBoard.PostNoticeBoardRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.admin.noticeBoard.GetNoticeBoardListResponseDto;
import com.travelproject.travelproject.dto.response.admin.noticeBoard.GetNoticeBoardResponseDto;
import com.travelproject.travelproject.entity.NoticeBoardEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.NoticeBoardRepository;
import com.travelproject.travelproject.service.admin.NoticeBoardService;

@Service
public class NoticeBoardServiceImplement implements NoticeBoardService {

    private NoticeBoardRepository noticeBoardRepository;

    @Autowired
    public NoticeBoardServiceImplement(NoticeBoardRepository noticeBoardRepository) {
        this.noticeBoardRepository = noticeBoardRepository;
    }

    //* 공지사항 게시물 작성
    @Override
    public ResponseEntity<ResponseDto> postNoticBoard(UserToken userToken, PostNoticeBoardRequestDto dto) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        try {
            NoticeBoardEntity noticeBoardEntity = new NoticeBoardEntity(dto);
            noticeBoardRepository.save(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }

    //* 공지사항 게시판 목록 조회
    @Override
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticBoardList(UserToken userToken) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;
        

        GetNoticeBoardListResponseDto body =null;
        try {
            List<NoticeBoardEntity> noticeBoardEntities = noticeBoardRepository.getList();
            body = new GetNoticeBoardListResponseDto(noticeBoardEntities); 
        } catch (Exception exception) {
            exception.printStackTrace();
        }
       
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //* 특정 공지사항 게시물 조회
    @Override
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticBoard(UserToken userToken, Integer noticeBoardNumber) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (noticeBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;

        GetNoticeBoardResponseDto body = null;

        try {
            
            //* 존재하지 않는 공지사항 게시물 번호 반환
            NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByNoticeBoardNumber(noticeBoardNumber);
            if (noticeBoardEntity == null) return ResponseMessage.NOT_EXIST_NOTICE_BOARD_NUMBER;

            body = new GetNoticeBoardResponseDto(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    //* 특정 공지사항 게시물 수정
    @Override
    public ResponseEntity<ResponseDto> patchNoticBoard(UserToken userToken, PatchNoticeBoardRequestDto dto) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        int noticeBoardNumber = dto.getNoticeBoardNumber();
        String noticeBoardTitle = dto.getNoticeBoardTitle();
        String noticeBoardContent = dto.getNoticeBoardContent();

        try {
            //* 존재하지 않는 공지사항 게시물 번호 반환
            NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByNoticeBoardNumber(noticeBoardNumber);
            if (noticeBoardEntity == null) return ResponseMessage.NOT_EXIST_NOTICE_BOARD_NUMBER;

            noticeBoardEntity.setTitle(noticeBoardTitle);
            noticeBoardEntity.setContent(noticeBoardContent);

            noticeBoardRepository.save(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

       return ResponseMessage.SUCCESS; 
    }

    @Override
    public ResponseEntity<ResponseDto> deleteNoticBoard(UserToken userToken, Integer noticeBoardNumber) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        if (noticeBoardNumber == null) return ResponseMessage.VAILDATION_FAILED;

        try {
            //* 존재하지 않는 공지사항 게시물 번호 반환
            NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByNoticeBoardNumber(noticeBoardNumber);
            if (noticeBoardEntity == null) return ResponseMessage.NOT_EXIST_NOTICE_BOARD_NUMBER;

            noticeBoardRepository.delete(noticeBoardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;
    }
    

    
}
