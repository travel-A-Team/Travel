package com.travelproject.travelproject.dto.request.admin.noticeBoard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchNoticeBoardRequestDto {
    @NotNull
    private Integer noticeBoardNumber;
    @NotBlank
    private String noticeBoardTitle;
    @NotBlank
    private String noticeBoardContent;
}
