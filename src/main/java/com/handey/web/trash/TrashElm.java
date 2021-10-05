package com.handey.web.trash;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "trash_elm")
@DynamicInsert
@DynamicUpdate
public class TrashElm {
    @Id
    @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "completed")
    private boolean completed;

    @ManyToOne
    @JoinColumn(name="trash_box_id")
    @JsonBackReference
    private TrashBox trashBox;
}
