package com.travelproject.travelproject.dto.request.adminNoticeBoard;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostNoticeBoardRequestDto {
    @NotBlank
    private String noticeBoardTitle;
    @NotBlank
    private String noticeBoardContent;
}
