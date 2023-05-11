package com.cloud.survey.repository;

import com.cloud.survey.entity.Survey;
import com.cloud.survey.entity.SurveyTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SurveyTargetRepository extends JpaRepository<SurveyTarget, Integer> {
    @Query(value= "select st.survey from SurveyTarget st where st.targetId=:targetId",
            countQuery = "select count(c) from SurveyTarget st where st.targetId=:targetId")
    Page<Survey> findByTargetId(Pageable pageable,String targetId);
}
