package com.handey.web.finishedweekly;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "fw_box")
@DynamicInsert
@DynamicUpdate
public class FwBox {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "savedt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate saveDt;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "weekly_box_id")
    private WeeklyBox weeklyBox;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @OneToMany(mappedBy = "fwBox")  //한 title이 여러 elm을 소지해도 된다. 양방향 관계의 주체가 되는 것은 weeklyBox
    @JsonManagedReference
    private List<FwElm> fwElmList = new ArrayList<>();

    @Transactional
    public void updateTitle(String newTitle) {
        if(newTitle != null)
            this.title = newTitle;
    }
}
