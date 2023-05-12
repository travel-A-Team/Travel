package com.travelproject.travelproject.dto.request.questionBoard;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostQuestionBoardRequestDto {

    @NotBlank
    private String questionBoardTitle;
    @NotBlank
    private String questionBoardContent;
  
}