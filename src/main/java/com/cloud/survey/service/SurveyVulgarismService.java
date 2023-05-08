package com.cloud.survey.service;


import com.cloud.survey.dto.vulgarism.VulgarismDTO;
import com.cloud.survey.entity.SurveyVulgarism;

import java.util.ArrayList;
import java.util.List;

public interface SurveyVulgarismService {
    void InsertVulgarism(int surID, boolean InfoYn, boolean QuestionYn);

    List<VulgarismDTO> getVulgarismList();

//    default VulgarismDTO entityToDTO(SurveyVulgarism surveyVulgarism) {
//        VulgarismDTO dto = VulgarismDTO.builder()
//                .surId(surveyVulgarism.getSurId())
//                .InfoYn(surveyVulgarism.isInfoYn())
//                .questionYn(surveyVulgarism.isQuestionYn())
//                .build();
//        return dto;
//    }
}
