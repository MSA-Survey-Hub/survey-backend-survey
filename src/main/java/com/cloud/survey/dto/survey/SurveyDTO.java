package com.cloud.survey.dto.survey;

import com.cloud.survey.entity.IsYn;
import com.cloud.survey.entity.SurveyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyDTO {

    private int surId;
    private String title;
    private String description;
    private int categoryId;
    private String categoryContent;
    private int version;
    private SurveyStatus status;
    private LocalDateTime dueDt;
    private IsYn isLoginYn;
    private IsYn isPrivateYn;
    private IsYn isModifyYn;
    private IsYn isAnnoyYn;
    private String regId;
    private LocalDateTime regDt;

}
