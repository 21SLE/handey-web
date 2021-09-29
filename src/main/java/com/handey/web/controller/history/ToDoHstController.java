package com.handey.web.controller.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.service.ToDoBoxHstService;
import com.handey.web.service.ToDoElmHstService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoHstController {
    private final ToDoBoxHstService toDoBoxHstService;
    private final ToDoElmHstService toDoElmHstService;

    public ToDoHstController(ToDoBoxHstService toDoBoxHstService, ToDoElmHstService toDoElmHstService) {
        this.toDoBoxHstService = toDoBoxHstService;
        this.toDoElmHstService = toDoElmHstService;
    }

    @GetMapping("/history/toDoBoxList")
    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstService.getToDoBoxHstList();
    }

    @DeleteMapping("/history/toDoBox/{toDoBoxHstId}")
    public boolean deleteToDoBoxHst(@PathVariable Long toDoBoxHstId) {
        toDoBoxHstService.deleteToDoBoxHst(toDoBoxHstId);
        return true;
    }

    @GetMapping("/history/toDoBox")
    public List<ToDoBoxHst> getToDoBoxHstListByDate(@RequestBody ToDoHstParam param) {
        return toDoBoxHstService.getToDoBoxHstListByDate(param.getSearchDt());
    }

    @GetMapping("/history/toDoElmList")
    public List<ToDoElmHst> getToDoElmList() { return toDoElmHstService.getToDoElmHstList();}

    @DeleteMapping("/history/toDoElm/{toDoElmHstId}")
    public boolean deleteToDoElmHst(@PathVariable Long toDoElmHstId) {
        toDoElmHstService.deleteToDoElmHst(toDoElmHstId);
        return true;
    }
}
