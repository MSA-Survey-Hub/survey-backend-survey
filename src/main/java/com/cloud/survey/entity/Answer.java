package com.cloud.survey.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
public class Answer {

    @Comment("답변 아이디")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ans_id", nullable = false)
    private int ansId;

    @Comment("질문 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "que_id", nullable = false)
    private Question question;

    @Comment("답변 종류")
    @Column(name = "type", nullable = false)
    private int type;

    @Comment("답변 내용")
    @Column(name = "content", length = 50, nullable = false)
    private String content;

    @Comment("답변 생성자 id")
    @Column(name = "reg_id")
    private String regId;

    @Comment("답변 생성자 ip")
    @Column(name = "reg_ip", length = 50)
    private String regIp;

    @Comment("답변 생성일자")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Comment("답변 수정자")
    @Column(name = "mod_id", length = 20)
    private String modId;

    @Comment("답변 수정일자")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Comment("답변 삭제여부")
    @ColumnDefault("false")
    @Column(name = "del_yn")
    private Boolean delYn;

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeModId(String modId) {
        this.modId = modId;
    }

}
