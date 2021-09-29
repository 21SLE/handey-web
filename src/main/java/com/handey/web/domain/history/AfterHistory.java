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
@Table(name = "after_history")
@DynamicInsert
@DynamicUpdate
public class AfterHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private java.sql.Timestamp dateField;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "subtitle")
    @ColumnDefault("0")
    private boolean subtitle;

}
