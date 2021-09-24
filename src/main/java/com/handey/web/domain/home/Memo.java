package com.handey.web.domain.home;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Getter
@Setter
@Table(name = "memo")
@DynamicInsert
@DynamicUpdate
public class Memo {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "content")
    private String content;

    @Transactional
    public void updateMemo(String newContent) {
        if(newContent != null)
            this.content = newContent;
    }
}
