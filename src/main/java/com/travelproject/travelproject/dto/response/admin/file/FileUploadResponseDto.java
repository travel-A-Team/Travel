package com.travelproject.travelproject.dto.response.admin.file;

import com.travelproject.travelproject.dto.response.ResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUploadResponseDto extends ResponseDto {
    
    private String fileImageUrl;
    public FileUploadResponseDto( String touristSpotImageFileUrl) {
        super("SU", "Success");
        this.fileImageUrl = touristSpotImageFileUrl;
    }
}
