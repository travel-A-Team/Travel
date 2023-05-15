package com.travelproject.travelproject.service.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.travelproject.travelproject.dto.response.admin.file.FileUploadResponseDto;
import com.travelproject.travelproject.provider.UserToken;

public interface FileService {
    public ResponseEntity<? super FileUploadResponseDto> upload(UserToken userToken ,MultipartFile file);
}
