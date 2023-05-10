package com.cloud.survey.repository;

import com.cloud.survey.entity.Survey;
import com.cloud.survey.entity.SurveyTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyTargetRepository extends JpaRepository<SurveyTarget, Integer> {
    Optional<List<SurveyTarget>> findByTargetId(String targetId);
}
