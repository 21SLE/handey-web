package com.handey.web.domain.history;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.handey.web.domain.history.WeeklyBox;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Getter
@Setter
@Table(name = "weekly_elm")
@DynamicInsert
@DynamicUpdate
public class WeeklyElm {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "completed")
    @ColumnDefault("0")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name="weekly_box_id")
    @JsonBackReference  //양방향 관계에서 종
    private WeeklyBox weeklyBox;

    public void update(String newContent, boolean newCompleted) {
        this.completed = newCompleted;
        if(newContent != null)
            this.content = newContent;
    }

    @Transactional
    public void updateContent(String newContent) {
        if(newContent != null)
            this.content = newContent;
    }

    @Transactional
    public void updateCompleted(boolean newCompleted) {
        this.completed = newCompleted;
    }


}
