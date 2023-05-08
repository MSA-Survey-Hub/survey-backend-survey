package com.cloud.survey.dto.survey;

import com.cloud.survey.dto.answer.AnswerDTO;
import com.cloud.survey.dto.question.QuestionDTO;
import com.cloud.survey.entity.SurveyTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDTO {
    SurveyDTO survey;
    List<QuestionDTO> questionDTOList;
}
