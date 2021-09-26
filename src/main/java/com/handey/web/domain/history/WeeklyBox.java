package com.handey.web.domain.history;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "weekly_box")
@DynamicInsert
@DynamicUpdate
public class WeeklyBox {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "clear")
    @ColumnDefault("0")
    private boolean clear;

    @OneToMany(mappedBy = "weeklyBox")  //한 title이 여러 elm을 소지해도 된다. 양방향 관계의 주체가 되는 것은 weeklyBox
    @JsonManagedReference   //양방향에서 주
    private List<WeeklyElm> weeklyElmList = new ArrayList<WeeklyElm>();


    public void update(String newTitle, boolean newClear) {
        this.clear = newClear;
        if(newTitle != null)
            this.title = newTitle;
    }

    @Transactional
    public void updateTitle(String newTitle) {
        if(newTitle != null)
            this.title = newTitle;
    }

    @Transactional
    public void updateClear(boolean newClear) {
        this.clear = newClear;
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
    public boolean getClear() {
        return clear;
    }
//
    public void setClear(boolean clear) { this.clear = clear; }
}
