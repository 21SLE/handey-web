package com.handey.web.domain.home;

import javax.persistence.*;

@Entity
@Table(name = "todo_box")
public class ToDoBox {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 ++해서 id 생성하는 것 = identity 전략
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "fixed")
    private boolean fixed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}
