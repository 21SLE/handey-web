package com.handey.web.domain.home;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todo_box")
@DynamicInsert
@DynamicUpdate
public class ToDoBox {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 ++해서 id 생성하는 것 = identity 전략
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "fixed")
    @ColumnDefault("0")
    private boolean fixed;

    @OneToMany(mappedBy = "toDoBox")
    @JsonManagedReference
    private List<ToDoElm> toDoElmList = new ArrayList<ToDoElm>();


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
//    public boolean isFixed() {
//        return fixed;
//    }
//
//    public void setFixed(boolean fixed) {
//        this.fixed = fixed;
//    }
//
//    public List<ToDoElm> getToDoElmList() {
//        return toDoElmList;
//    }
//
//    public void setToDoElmList(List<ToDoElm> toDoElmList) {
//        this.toDoElmList = toDoElmList;
//    }
}
