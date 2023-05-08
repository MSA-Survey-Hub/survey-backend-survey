package com.cloud.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "survey_vulgarism")
public class SurveyVulgarism {
    @Id
    @Column(name="sur_id", nullable = false)
    private int surId;

    @Comment("설문 기본 정보에 비속어 있는지 여부")
    @Column(name="info_yn", nullable = false)
    private boolean infoYn;

    @Comment("설문 질문 정보에 비속어 있는지 여부")
    @Column(name="question_yn", nullable = false)
    private  boolean questionYn;
}
