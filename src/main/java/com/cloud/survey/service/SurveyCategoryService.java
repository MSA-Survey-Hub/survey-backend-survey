package com.cloud.survey.service;

import com.cloud.survey.dto.PageRequestDTO;
import com.cloud.survey.dto.PageResultDTO;
import com.cloud.survey.dto.survey.SurveyCategoryDTO;
import com.cloud.survey.entity.SurveyCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SurveyCategoryService {
    PageResultDTO<SurveyCategoryDTO, SurveyCategory> getCategoryList(PageRequestDTO requestDTO);

     List<SurveyCategoryDTO> getCategorySelectList();

    ResponseEntity<String> insertCategory(SurveyCategoryDTO surveyCategoryDTO);
    ResponseEntity<String> deleteCategory(List<Integer> surCatIdList);

    default SurveyCategory dtoToEntity(SurveyCategoryDTO dto) {
        SurveyCategory surveyCategory = SurveyCategory.builder()
                .content(dto.getContent())
                .build();
        return surveyCategory;
    }

    default SurveyCategoryDTO entityToDTO(SurveyCategory surveyCategory) {
        SurveyCategoryDTO dto = SurveyCategoryDTO.builder()
                .surCatId(surveyCategory.getSurCatId())
                .content(surveyCategory.getContent())
                .regDt(surveyCategory.getRegDt())
                .build();
        return dto;
    }

}
