package com.travelproject.travelproject.dto.request.questionBoard;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchQuestionBoardRequestDto {
    @NotNull
    private Integer questionBoardNumber;
    @NotBlank
    @Email
    private String questionBoardWriterEmail;
    @NotBlank
    private String questionBoardTitle;
    @NotBlank
    private String questionBoardContent;
}
