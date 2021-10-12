package com.handey.web.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handey.web.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@DynamicInsert
@DynamicUpdate
public class Schedule {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "startDt")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDt;

    @Column(name = "endDt")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDt;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Transactional
    public void updateSchedule(ScheduleParam param) {
        String newContent = param.getContent();
        LocalDateTime newStartDt = param.getStartDt();
        LocalDateTime newEndDt = param.getEndDt();

        if(newContent != null)
            this.content = newContent;
        if(newStartDt != null)
            this.startDt = newStartDt;
        if(newEndDt != null)
            this.endDt = newEndDt;
    }

}
