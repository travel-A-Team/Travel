package com.travelproject.travelproject.service.Implement.admin;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.response.admin.file.FileUploadResponseDto;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.service.admin.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${imageFile.path}")
    private String IMAGE_FILE_PATH;
    @Value("${imageFile.url}")
    private String IMAGE_FILE_URL;

    @Override
    public ResponseEntity<? super FileUploadResponseDto> upload(UserToken userToken, MultipartFile file) {

        if (file.isEmpty()) return ResponseMessage.VAILDATION_FAILED;

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        FileUploadResponseDto body  = null;
        
        //* 이미지 이름 가져오기
        String originalImageFileName = file.getOriginalFilename();

        //* 확장자 가져오기
        int extensionIndex = originalImageFileName.lastIndexOf(".");
        String extension = originalImageFileName.substring(extensionIndex);

        //* 이미지 파일 이름 변경
        String uuid = UUID.randomUUID().toString();
        String saveImageFileName = uuid + extension; 

        //* 이미지 파일 저장 경로 지정
        String saveImageFilePath = IMAGE_FILE_PATH + saveImageFileName;


        try {
            //* 물리적인 파일을 저장
            file.transferTo(new File(saveImageFilePath));

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.FILE_UPLOAD_ERROR;
        }


        String touristSpotImageFileUrl = IMAGE_FILE_URL + saveImageFileName;
        body = new FileUploadResponseDto(touristSpotImageFileUrl);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    
}
