package com.handey.web.controller.home;

import lombok.Data;

@Data
public class ToDoElmParam {
    private String content;

    private boolean completed;

    private Long toDoBoxId;
}
