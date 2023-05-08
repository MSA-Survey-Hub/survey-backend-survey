package com.cloud.survey.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Survey {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Comment("설문 고유값")
        @Column(name = "sur_id", nullable = false)
        private int surId;

        @Comment("설문 제목")
        @Column(name="title", nullable = false, length = 50)
        private String title;

        @Comment("설문 상세")
        @Column(name="description", nullable = false, length = 200)
        private String description;

        @Comment("설문 카테고리")
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", nullable = false)
        private SurveyCategory surveyCategory;

        @Comment("설문 버전")
        @Column(name="version", nullable = false)
        private int version;

        @Comment("설문 상태")
        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private SurveyStatus status;

        @Comment("설문 마감기한")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        @Column(name = "due_dt", nullable = false)
        private LocalDateTime dueDt;

        @Comment("설문 조회수")
        @ColumnDefault("0")
        private Integer views;

        @Comment("로그인 여부")
        @Enumerated(EnumType.STRING)
        @Column(name = "is_login", nullable = false)
        private IsYn isLoginYn;

        @Comment("설문 공개 여부")
        @Enumerated(EnumType.STRING)
        @Column(name = "is_private", nullable = false)
        private IsYn isPrivateYn;

        @Comment("답변 수정 여부")
        @Column(name = "is_modify", nullable = false)
        @Enumerated(EnumType.STRING)
        private IsYn isModifyYn;

        @Comment("익명 답변 여부")
        @Enumerated(EnumType.STRING)
        @Column(name = "is_annoy", nullable = false)
        private IsYn isAnnoyYn;

        @Comment("설문 생성자")
        @Column(name = "reg_id", nullable = false, length = 20)
        private String regId;

        @Comment("설문 생성일자")
        @CreatedDate
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        @Column(name = "reg_dt", nullable = false)
        private LocalDateTime regDt;

        @Comment("설문 수정자")
        @Column(name = "mod_id", length = 20)
        private String ModId;

        @Comment("설문 수정일자")
        @LastModifiedDate
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        @Column(name = "mod_dt")
        private LocalDateTime modDt;

}
