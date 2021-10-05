package com.handey.web.memo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.handey.web.member.Member;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Transactional
    public void updateMemo(String newContent) {
        if(newContent != null)
            this.content = newContent;
    }
}
