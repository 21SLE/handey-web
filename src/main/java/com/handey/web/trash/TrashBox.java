package com.handey.web.trash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.handey.web.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "trash_box")
@DynamicInsert
@DynamicUpdate
public class TrashBox {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 ++해서 id 생성하는 것 = identity 전략
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "notitle")
    private boolean noTitle;


    @Column(name = "registerdt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate registerDt;

    @Column(name = "enddt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDt;

    @OneToMany(mappedBy = "trashBox")
    @JsonManagedReference
    private List<TrashElm> trashElmList = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;
}
