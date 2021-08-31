package com.handey.web.domain.home;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Setter
@Getter
//@ToString(exclude = {"toDoBox"})
@Table(name = "todo_elm")
@DynamicInsert
@DynamicUpdate
public class ToDoElm {
//    public static void main(String args[]) {
//        ToDoElm myClass = new ToDoElm();
//        System.out.println("ToString::" + myClass);
//    }

    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "completed")
    @ColumnDefault("0")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name="to_do_box_id")
    private ToDoBox toDoBox;

    @Override public String toString() {
        return "ToStringExample(" + this.getId() + ", " + this.getContent()
                + ", " + this.getToDoBox().getId() + ")";
    }

}
