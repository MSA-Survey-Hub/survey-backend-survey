package com.cloud.survey.controller;

import com.cloud.survey.dto.PageRequestDTO;
import com.cloud.survey.dto.PageResultDTO;
import com.cloud.survey.dto.survey.SurveyCategoryDTO;
import com.cloud.survey.entity.SurveyCategory;
import com.cloud.survey.service.SurveyCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value="v1/survey/category")
@RequiredArgsConstructor
public class SurveyCategoryController {
    private final SurveyCategoryService surveyCategoryService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<SurveyCategoryDTO, SurveyCategory>> getCategoryList(PageRequestDTO pageRequestDTO) {
        PageResultDTO<SurveyCategoryDTO, SurveyCategory> categoryList = surveyCategoryService.getCategoryList(pageRequestDTO);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/selectlist")
    public ResponseEntity<List<SurveyCategoryDTO>> getCategorySelectList() {
        return new ResponseEntity<>( surveyCategoryService.getCategorySelectList(), HttpStatus.OK);
    }

    @PostMapping("/reg")
    public ResponseEntity<String> registerCategory(@RequestBody SurveyCategoryDTO surveyCategoryDTO) {
        return surveyCategoryService.insertCategory(surveyCategoryDTO);
    }

    @PostMapping("/del")
    public ResponseEntity<String> deleteCategory(@RequestBody List<Integer> surCatIdList) {
        return surveyCategoryService.deleteCategory(surCatIdList);
    }
}
