package com.handey.web.controller.home;

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
    public Long createToDoBoxObj(@PathVariable Long toDoBoxId) {
        return toDoElmService.createToDoElmObj(toDoBoxId);
    }

    /**
     * todoelm 내용 수정
     */
    @PutMapping("/toDoElm/{toDoElmId}")
    public boolean updateToDoElmContent(@PathVariable Long toDoElmId, @RequestBody ToDoElmParam param) {
        return toDoElmService.updateToDoElmContent(toDoElmId, param);
    }

    /**
     * todoElm completed여부 수정
     */
    @PatchMapping("/toDoElm/{toDoElmId}")
    public boolean updateToDoElmCompletedYn(@PathVariable Long toDoElmId) {
        return toDoElmService.updateToDoElmCompletedYn(toDoElmId);
    }

    /**
     * todoelm 삭제
     */
    @DeleteMapping("/toDoElm/{toDoElmId}")
    public boolean deleteToDoElmContent(@PathVariable Long toDoElmId) {
        toDoElmService.deleteToDoElm(toDoElmId);
        return true;
    }

}
