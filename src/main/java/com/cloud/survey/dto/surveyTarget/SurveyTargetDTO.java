package com.cloud.survey.dto.surveyTarget;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyTargetDTO {
    private int surveyId;
    private int targetId;
}
