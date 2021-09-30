package com.handey.web.domain.home;

import com.fasterxml.jackson.annotation.*;
import com.handey.web.domain.join.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
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

    @Column(name = "notitle")
    private boolean noTitle;

    @Column(name = "fixed")
    @ColumnDefault("0")
    private boolean fixed;

    @OneToMany(mappedBy = "toDoBox")
    @JsonManagedReference
    private List<ToDoElm> toDoElmList = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

   @Transactional
   public void update(ToDoBox toDoBox) {
       String newTitle = toDoBox.getTitle();
       boolean newFixedVal = toDoBox.isFixed();

       if(newTitle != null)
           this.title = newTitle;
       this.fixed = newFixedVal;
   }

    @Transactional
    public void updateTitle(String newTitle) {
        if(newTitle != null)
            this.title = newTitle;
    }

    @Transactional
    public void updateNoTitle(boolean newNoTitle) { this.noTitle = newNoTitle; }

    @Transactional
    public void updateFixedYn(boolean newFixedYn) {
        this.fixed = newFixedYn;
    }
}
