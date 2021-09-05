package com.handey.web.domain.home;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

    @Column(name = "title")
    private String title;

    @Column(name = "completed")
    @ColumnDefault("0")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name="weekly_box_id")
    @JsonBackReference  //양방향 관계에서 종
    private WeeklyBox weeklyBox;

}
