package com.handey.web.todo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Getter
@Setter
@Table(name = "todo_elm")
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

    @Transactional
    public void update(ToDoElm toDoElm) {
        String newContent = toDoElm.getContent();
        boolean newCompletedYn = toDoElm.isCompleted();

        if(newContent != null)
            this.content = newContent;
        this.completed = newCompletedYn;
    }

    @Transactional
    public void updateContent(String newContent) {
        if(newContent != null)
            this.content = newContent;
    }

    @Transactional
    public void updateCompletedYn(boolean newCompletedYn) {
        this.completed = newCompletedYn;
    }

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
