package com.handey.web.domain.home;

import javax.persistence.*;

@Entity
@Table(name = "weekly_box")
public class WeeklyBox {

    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;


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
}
