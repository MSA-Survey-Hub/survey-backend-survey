package com.cloud.survey.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SurveyTarget {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "no", nullable = false)
        private int no;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "sur_id", nullable = false)
        private Survey survey;

        @Comment("설문 대상자 고유값")
        @Column(name="target_id", nullable = false, length = 50)
        private String targetId;

}
