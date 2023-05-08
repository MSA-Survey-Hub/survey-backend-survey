package com.cloud.survey.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "survey_category")
@EntityListeners(AuditingEntityListener.class)
public class SurveyCategory {
    @Comment("설문 카테고리 아이디")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sur_cat_id", nullable = false)
    private Integer surCatId;

    @Comment("설문 카테고리 내용")
    @Column(name="content", nullable = false)
    private String content;

    @Comment("설문 카테고리 생성일자")
    @CreatedDate
    @Column(name = "reg_dt", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;
}
