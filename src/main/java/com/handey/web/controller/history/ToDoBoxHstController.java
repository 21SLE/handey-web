package com.handey.web.controller.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.service.ToDoBoxHstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoBoxHstController {
    private final ToDoBoxHstService toDoBoxHstService;

    public ToDoBoxHstController(ToDoBoxHstService toDoBoxHstService) {
        this.toDoBoxHstService = toDoBoxHstService;
    }

    @GetMapping("/history/toDoBoxList")
    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstService.getToDoBoxHstList();
    }

    @PostMapping("/history/toDoBox")
    public Long createToDoBoxHst(@RequestBody ToDoBoxHst toDoBoxHst) {
        return toDoBoxHstService.createToDoBoxHst(toDoBoxHst);

    }
}
