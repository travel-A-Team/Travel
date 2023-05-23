package com.travelproject.travelproject.dto.request.questionBoard;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchCommentBoardRequestDto {
    
    @NotNull
    private Integer questionBoardNumber;
    @NotBlank
    private String commentContent;
}
