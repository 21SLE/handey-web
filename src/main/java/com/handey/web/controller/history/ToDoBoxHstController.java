package com.handey.web.controller.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.service.ToDoBoxHstService;
import com.handey.web.service.ToDoElmHstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoBoxHstController {
    private final ToDoBoxHstService toDoBoxHstService;
    private final ToDoElmHstService toDoElmHstService;

    public ToDoBoxHstController(ToDoBoxHstService toDoBoxHstService, ToDoElmHstService toDoElmHstService) {
        this.toDoBoxHstService = toDoBoxHstService;
        this.toDoElmHstService = toDoElmHstService;
    }

    @GetMapping("/history/toDoBoxList")
    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstService.getToDoBoxHstList();
    }

    /**
     * 테스트용 api (백엔드에서 매일 이 작업 반복되게 개발될 예정)
     */
    @PostMapping("/history/toDoBox")
    public Long createToDoBoxHst(@RequestBody ToDoBoxHst toDoBoxHst) {
        Long toDoBoxHstId = toDoBoxHstService.createToDoBoxHst(toDoBoxHst);

        toDoBoxHst.getToDoElmHstList().forEach(toDoElmHst -> {
            toDoElmHst.setToDoBoxHst(toDoBoxHst);
            toDoElmHstService.createToElmHst(toDoElmHst);
        });

        return toDoBoxHstId;

    }
}
