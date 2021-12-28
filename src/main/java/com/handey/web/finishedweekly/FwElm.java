package com.handey.web.finishedweekly;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Getter
@Setter
@Table(name = "fw_elm")
@DynamicInsert
@DynamicUpdate
public class FwElm {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="fw_box_id")
    @JsonBackReference  //양방향 관계에서 종
    private FwBox fwBox;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "weekly_elm_id")
    private WeeklyElm weeklyElm;

    @Transactional
    public void updateContent(String newContent) {
        if(newContent != null)
            this.content = newContent;
    }
}
