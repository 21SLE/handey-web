package com.handey.web.todohistory;

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

    /**
     * 투두 히스토리 박스 전체 조회
     */
    @GetMapping("/history/todos")
    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstService.getToDoBoxHstList();
    }

    /**
     * 투두 히스토리 박스 날짜별 조회
     */
    @GetMapping("/history/todos/date")
    public List<ToDoBoxHst> getToDoBoxHstListByDate(@RequestBody ToDoHstParam param) {
        return toDoBoxHstService.getToDoBoxHstListByDate(param.getSearchDt());
    }

    /**
     * 회원 투두 히스토리 박스 전체 조회
     */
    @GetMapping("/user/{userId}/history/todos")
    public List<ToDoBoxHst> getToDoBoxHstListByUserId(@PathVariable Long userId) {
        return toDoBoxHstService.getToDoBoxHstListByUserId(userId);
    }

    /**
     * 회원 투두 히스토리 박스 날짜별 조회
     */
    @GetMapping("/user/{userId}/history/todos/date")
    public List<ToDoBoxHst> getToDoBoxHstListByUserIdAndDate(@PathVariable Long userId, @RequestBody ToDoHstParam param) {
        return toDoBoxHstService.getToDoBoxHstListByUserIdAndDate(userId, param.getSearchDt());
    }

    /**
     * 투두 히스토리 박스 삭제
     */
    @DeleteMapping("/history/toDoBox/{toDoBoxHstId}")
    public boolean deleteToDoBoxHst(@PathVariable Long toDoBoxHstId) {
        toDoBoxHstService.deleteToDoBoxHst(toDoBoxHstId);
        return true;
    }

    /**
     * 투두 히스토리 elm 전체 조회
     */
    @GetMapping("/history/toDoElmList")
    public List<ToDoElmHst> getToDoElmList() { return toDoElmHstService.getToDoElmHstList();}

    /**
     * 투두 히스토리 elm 삭제
     */
    @DeleteMapping("/history/toDoElm/{toDoElmHstId}")
    public boolean deleteToDoElmHst(@PathVariable Long toDoElmHstId) {
        toDoElmHstService.deleteToDoElmHst(toDoElmHstId);
        return true;
    }
}
