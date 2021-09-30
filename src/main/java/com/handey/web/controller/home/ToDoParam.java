package com.handey.web.controller.home;

import com.handey.web.domain.home.ToDoElm;
import lombok.Data;

import java.util.List;

@Data
public class ToDoParam {
    private Long userId;

    private String title;

    private boolean fixed;

    private List<ToDoElm> toDoElmList;
}
