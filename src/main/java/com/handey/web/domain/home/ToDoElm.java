package com.handey.web.domain.home;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
//import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@ToString(exclude = "toDoBox")
@Table(name = "todo_elm")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id") // 추가
@DynamicInsert
@DynamicUpdate
public class ToDoElm {
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
    @JsonBackReference
    private ToDoBox toDoBox;


//    public ToDoElm() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(boolean completed) {
//        this.completed = completed;
//    }
//
//    public ToDoBox getToDoBox() {
//        return toDoBox;
//    }
//
//    public void setToDoBox(ToDoBox toDoBox) {
//        this.toDoBox = toDoBox;
//    }
}
