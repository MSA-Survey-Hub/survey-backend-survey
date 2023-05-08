package com.cloud.survey.dto.answer;

import com.cloud.survey.entity.QuestionOption;
import com.cloud.survey.entity.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerQuestionDTO {
    private int ansId;
    private int ansType;
    private String ansContent;
    private String regId;
    private int queId;
    private int surId;
    private QuestionType queType;
    private String queContent;
    private List<QuestionOption> optionList;

    public void addOptionList(QuestionOption questionOption) {
        optionList.add(questionOption);
    }

}
