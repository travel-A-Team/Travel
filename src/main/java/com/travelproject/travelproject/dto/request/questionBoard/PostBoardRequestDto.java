package com.travelproject.travelproject.dto.request.questionBoard;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardRequestDto {
    @NotBlank
    @Email
    private String questionBoardWriterEmail;
    @NotBlank
    private String questionBoardTitle;
    @NotBlank
    private String questionBoardContent;
}