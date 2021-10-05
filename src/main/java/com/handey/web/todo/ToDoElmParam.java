package com.handey.web.todo;

import lombok.Data;

@Data
public class ToDoElmParam {
    private String content;

    private boolean completed;

    private Long toDoBoxId;
}
