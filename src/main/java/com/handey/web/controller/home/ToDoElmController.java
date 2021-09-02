package com.handey.web.controller.home;

import com.handey.web.domain.home.ToDoElm;
import com.handey.web.service.ToDoElmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoElmController {
    private final ToDoElmService toDoElmService;

    @Autowired
    public ToDoElmController(ToDoElmService toDoElmService) {
        this.toDoElmService = toDoElmService;
    }

    /**
     * todoelm 객체 생성
     */
    @PostMapping("/toDoBox/{toDoBoxId}")
    public Long createToDoBoxObj(@PathVariable long toDoBoxId) {
        return toDoElmService.createToDoElmObj(toDoBoxId);
    }

    /**
     * todoelm 내용 수정
     */
    @PutMapping("/toDoElm/{toDoElmId}")
    public boolean updateToDoElmContent(@PathVariable long toDoElmId, @RequestBody ToDoElmParam param) {
        return toDoElmService.updateToDoElmContent(toDoElmId, param);
    }

}
