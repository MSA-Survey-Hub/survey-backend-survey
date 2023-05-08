package com.cloud.survey.repository;

import com.cloud.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("select q from Question q where q.survey.surId = :surId")
    List<Question> findBySurId(int surId);


}
