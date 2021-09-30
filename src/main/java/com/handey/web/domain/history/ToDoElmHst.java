package com.handey.web.domain.history;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.handey.web.domain.home.ToDoBox;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "todo_elm_hst")
@DynamicInsert
@DynamicUpdate
public class ToDoElmHst {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="todo_box_hst_id")
    @JsonBackReference
    private ToDoBoxHst toDoBoxHst;
}
