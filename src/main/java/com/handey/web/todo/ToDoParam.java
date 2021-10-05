package com.handey.web.todo;

import com.handey.web.todo.ToDoElm;
import lombok.Data;

import java.util.List;

@Data
public class ToDoParam {
    private Long userId;

    private String title;

    private boolean fixed;

    private List<ToDoElm> toDoElmList;
}
