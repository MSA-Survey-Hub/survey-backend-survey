package com.cloud.survey.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private int ansId;
    private int queId;
    private int type;
    private String content;
    private String regId;

}
