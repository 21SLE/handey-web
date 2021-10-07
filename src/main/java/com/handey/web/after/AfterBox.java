package com.handey.web.after;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyElm;
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
@Table(name = "after_box")
@DynamicInsert
@DynamicUpdate
public class AfterBox {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "subtitle")
    private boolean subtitle;

    @Column(name = "clear")
    private boolean clear;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Transactional
    public void updateClear(boolean newClear) {
        this.clear = newClear;
    }

    @Transactional
    public void updateSubtitle(boolean newSubtitle) {
        this.subtitle = newSubtitle;
    }

}
