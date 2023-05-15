package com.travelproject.travelproject.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travelproject.travelproject.common.constant.RequestPattern;
import com.travelproject.travelproject.dto.response.admin.file.FileUploadResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.FileService;

@RestController
@RequestMapping(RequestPattern.ADMIN_FILE_API)
public class FileController {
    
    private FileService fileService;

    private final String UPLOAD_URL = "upload";

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //* 파일 업로드
    @PostMapping(UPLOAD_URL)
    public ResponseEntity<? super FileUploadResponseDto> uploadFile(
        @AuthenticationPrincipal UserToken userToken,
        @RequestParam("file") MultipartFile file
    ) {
        ResponseEntity<? super FileUploadResponseDto> response = fileService.upload(userToken, file);
        return response;
    }


}
