package com.handey.web.todohistory;

import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoHstController {
    private final ToDoBoxHstService toDoBoxHstService;
    private final ToDoElmHstService toDoElmHstService;
    private final ResponseService responseService;

    public ToDoHstController(ToDoBoxHstService toDoBoxHstService, ToDoElmHstService toDoElmHstService, ResponseService responseService) {
        this.toDoBoxHstService = toDoBoxHstService;
        this.toDoElmHstService = toDoElmHstService;
        this.responseService = responseService;
    }

    /**
     * 투두 히스토리 박스 전체 조회
     */
    @GetMapping("/history/todos")
    public ListResponse<ToDoBoxHst> getToDoBoxHstList() {
        return responseService.returnListResponse(toDoBoxHstService.getToDoBoxHstList());
    }

    /**
     * 투두 히스토리 박스 날짜별 조회
     */
    @GetMapping("/history/todos/date")
    public ListResponse<ToDoBoxHst> getToDoBoxHstListByDate(@RequestBody ToDoHstParam param) {
        return responseService.returnListResponse(toDoBoxHstService.getToDoBoxHstListByDate(param.getSearchDt()));
    }

    /**
     * 회원 투두 히스토리 박스 전체 조회
     */
    @GetMapping("/user/{userId}/history/todos")
    public ListResponse<ToDoBoxHst> getToDoBoxHstListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(toDoBoxHstService.getToDoBoxHstListByUserId(userId));
    }

    /**
     * 회원 투두 히스토리 박스 날짜별 조회
     */
    @GetMapping("/user/{userId}/history/todos/date")
    public ListResponse<ToDoBoxHst> getToDoBoxHstListByUserIdAndDate(@PathVariable Long userId, @RequestBody ToDoHstParam param) {
        return responseService.returnListResponse(toDoBoxHstService.getToDoBoxHstListByUserIdAndDate(userId, param.getSearchDt()));
    }

    /**
     * 투두 히스토리 박스 삭제
     */
    @DeleteMapping("/user/history/toDoBox/{toDoBoxHstId}")
    public Response deleteToDoBoxHst(@PathVariable Long toDoBoxHstId) {
        toDoBoxHstService.deleteToDoBoxHst(toDoBoxHstId);
        return responseService.returnSuccessResponse();
    }

    /**
     * 투두 히스토리 elm 전체 조회
     */
    @GetMapping("/history/toDoElmList")
    public ListResponse<ToDoElmHst> getToDoElmList() { return responseService.returnListResponse(toDoElmHstService.getToDoElmHstList());}

    /**
     * 투두 히스토리 elm 삭제
     */
    @DeleteMapping("/user/history/toDoElm/{toDoElmHstId}")
    public Response deleteToDoElmHst(@PathVariable Long toDoElmHstId) {
        toDoElmHstService.deleteToDoElmHst(toDoElmHstId);
        return responseService.returnSuccessResponse();
    }
}
