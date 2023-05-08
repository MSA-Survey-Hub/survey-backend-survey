package com.cloud.survey.repository;

import com.cloud.survey.entity.SurveyVulgarism;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VulgarismRepository extends JpaRepository<SurveyVulgarism,Integer> {
}
