package com.cloud.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Question {
    @Comment("질문 아이디")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="que_id", nullable = false)
    private int queId;

    @Comment("설문 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sur_id", nullable = false)
    private Survey survey;

    @Comment("질문 종류")
    @Column(name = "q_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType qType;

    @Comment("질문 내용")
    @Column(name="content", nullable = false, length = 50)
    private String content;

    @Comment("질문 생성자")
    @Column(name="reg_id", length = 20, nullable = false)
    private String regId;

    @Comment("질문 생성일자")
    @Column(name = "reg_dt", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime regDt;

    @Comment("질문 수정자")
    @Column(name="mod_id", length = 20)
    private String modId;

    @Comment("질문 수정일자")
    @Column(name = "mod_dt")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime modDt;
}
