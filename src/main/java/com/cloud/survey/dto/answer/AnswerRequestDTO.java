package com.cloud.survey.dto.answer;

import com.cloud.survey.dto.answer.AnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDTO {
    Integer surId;
    List<AnswerDTO> answerDTOList;
}
