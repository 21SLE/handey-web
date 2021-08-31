package com.handey.web.domain.home;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Data
@Entity
@Getter
@Setter
//@ToString
//@ToString(exclude = {"toDoElmList"})
@Table(name = "todo_box")
@DynamicInsert
@DynamicUpdate
public class ToDoBox {
//    public static void main(String args[]) {
//        ToDoBox myClass = new ToDoBox();
//        System.out.println("ToString::" + myClass);
//    }

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 ++해서 id 생성하는 것 = identity 전략
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "fixed")
    @ColumnDefault("0")
    private boolean fixed;


    @OneToMany(mappedBy = "toDoBox")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private List<ToDoElm> toDoElmList = new ArrayList<ToDoElm>();


    @Override public String toString() {
        return "ToStringExample(" + this.getId() + ", " + this.getTitle()
                + ", " + ")";
    }
}
