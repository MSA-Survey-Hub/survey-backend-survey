package com.cloud.survey.entity;

import com.cloud.survey.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOption {
    @Comment("질문 옵션 아이디")
    @Id
    @Column(name = "que_opt_id", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer queOptId;

    @Comment("질문 아이디")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_id", nullable = false)
    private Question question;

    @Comment("질문 옵션 번호")
    @Column(name = "option_order", length = 10, nullable = false)
    private Integer optionOrder;

    @Comment("질문 옵션 내용")
    @Column(name = "option_name", length = 50, nullable = false)
    private String optionName;


}
