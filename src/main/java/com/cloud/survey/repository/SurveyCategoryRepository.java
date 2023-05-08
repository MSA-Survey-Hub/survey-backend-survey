package com.cloud.survey.repository;

import com.cloud.survey.entity.SurveyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SurveyCategoryRepository extends JpaRepository<SurveyCategory, Integer> {

    @Query("select sc from SurveyCategory sc where sc.surCatId = :surCatId")
    SurveyCategory findBySurCatId(Integer surCatId);

    @Query("select sc.surCatId from SurveyCategory sc where sc.content = :content")
    Integer findByContent(String content);

}
