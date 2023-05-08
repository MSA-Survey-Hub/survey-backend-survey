package com.cloud.survey.service;

import com.cloud.survey.dto.question.QuestionDTO;
import com.cloud.survey.entity.Answer;
import com.cloud.survey.entity.Question;
import com.cloud.survey.entity.QuestionOption;
import com.cloud.survey.entity.Survey;
import com.cloud.survey.repository.QuestionOptionRepository;
import com.cloud.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private final QuestionRepository questionRepository;

    public void insertSurveyQuestion(List<QuestionDTO> questionDTOList, Survey survey, String regId){

        questionDTOList.forEach(questionDTO -> {
            Question save = questionRepository.save(dtoToEntity(questionDTO, survey, regId));
            insertSurveyQuestionOption(questionDTO.getOptionList(), save);
        });
    }

    public void insertSurveyQuestionOption(List<QuestionOption> questionOptionList, Question question){
        questionOptionList.forEach(questionOption -> {
            questionOption.setQuestion(question);
            QuestionOption save = questionOptionRepository.save(questionOption);
        });
    }

    @Autowired
    private final QuestionOptionRepository questionOptionRepository;

    @Transactional
    public List<QuestionDTO> getSurveyQuestion (int surId){
        List<QuestionDTO> questionDtoList = new ArrayList<>();
        List<Question> questionList = questionRepository.findBySurId(surId);

        questionList.forEach(question -> {
            List<QuestionOption> questionOptionList = questionOptionRepository.findQuestionOptionByQueId(question.getQueId());
            QuestionDTO questionDTO = entityToDto(question, questionOptionList);
            questionDtoList.add(questionDTO);
        });

        return questionDtoList;
    }

}
