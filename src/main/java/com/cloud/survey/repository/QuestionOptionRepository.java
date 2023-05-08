package com.cloud.survey.repository;

import com.cloud.survey.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {
    @Query("select qo from QuestionOption qo where qo.question.queId = :queId")
    List<QuestionOption> findQuestionOptionByQueId(Integer queId);
}
