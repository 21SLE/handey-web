package com.handey.web.domain.join;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_info")
@DynamicInsert
@DynamicUpdate
public class UseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resetTime")
    private Long resetTime;

    @Column(name = "theme")
    @ColumnDefault("1")
    private Long theme;

//    @OneToOne
//    @JoinColumn(name = "userId")
//    private Member member;


}
