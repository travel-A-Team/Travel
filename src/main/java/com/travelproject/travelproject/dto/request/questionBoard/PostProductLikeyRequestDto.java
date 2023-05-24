package com.travelproject.travelproject.dto.request.questionBoard;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostProductLikeyRequestDto {

    @NotNull
    private Integer productBoardNumber;

}
