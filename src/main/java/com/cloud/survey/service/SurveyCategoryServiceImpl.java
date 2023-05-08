package com.cloud.survey.service;

import com.cloud.survey.dto.PageRequestDTO;
import com.cloud.survey.dto.PageResultDTO;
import com.cloud.survey.dto.survey.SurveyCategoryDTO;
import com.cloud.survey.dto.survey.SurveyDTO;
import com.cloud.survey.entity.Survey;
import com.cloud.survey.entity.SurveyCategory;
import com.cloud.survey.repository.SurveyCategoryRepository;
import com.cloud.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SurveyCategoryServiceImpl implements SurveyCategoryService {
    @Autowired
    private final SurveyCategoryRepository surveyCategoryRepository;

    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final ModelMapper mapper;

    @Override
    public PageResultDTO<SurveyCategoryDTO, SurveyCategory> getCategoryList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("regDt").descending());
        Page<SurveyCategory> surveyCategoryPage = surveyCategoryRepository.findAll(pageable);
        Function<SurveyCategory, SurveyCategoryDTO> fn = (surveyCategory -> entityToDTO(surveyCategory));
        return new PageResultDTO<>(surveyCategoryPage, fn);
    }

    public List<SurveyCategoryDTO> getCategorySelectList(){
        List<SurveyCategoryDTO> surveyCategoryDTOList = new ArrayList<>();
        List<SurveyCategory> list = surveyCategoryRepository.findAll();

        list.forEach(SurveyCategory -> {
            SurveyCategoryDTO surveyCategoryDTO = mapper.map(SurveyCategory, SurveyCategoryDTO.class);
            surveyCategoryDTOList.add(surveyCategoryDTO);
        });

        return surveyCategoryDTOList;
    }

    @Override
    public ResponseEntity<String> insertCategory(SurveyCategoryDTO surveyCategoryDTO) {
        Integer surCatId = surveyCategoryRepository.findByContent(surveyCategoryDTO.getContent());
        if (surCatId == null) {
            try {
                surveyCategoryRepository.save(dtoToEntity(surveyCategoryDTO));
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("카테고리 생성에 성공함", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("이미 존재하는 카테고리로 생성할 수 없음", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> deleteCategory(List<Integer> surCatIdList) {
        String errorMessage = "";
        for (Integer surCatId : surCatIdList) {
            List<Integer> surIdList = surveyRepository.findSurIdBySurCatId(surCatId);
            if (surIdList.isEmpty()) {
                try {
                    surveyCategoryRepository.deleteById(surCatId);
                } catch (Exception e) {
                    errorMessage = e.getMessage();
                }
            } else {
                errorMessage = "카테고리가 참조 중인 설문이 있어 삭제 불가함";
            }
        }
        if (errorMessage.length() != 0) {
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("카테고리 삭제에 성공함", HttpStatus.OK);
        }
    }
}
