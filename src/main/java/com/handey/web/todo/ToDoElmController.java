package com.handey.web.todo;

import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoElmController {
    private final ToDoElmService toDoElmService;
    private final ResponseService responseService;

    @Autowired
    public ToDoElmController(ToDoElmService toDoElmService, ResponseService responseService) {
        this.toDoElmService = toDoElmService;
        this.responseService = responseService;
    }

    /**
     * todoelm 객체 생성
     */
    @PostMapping("/user/toDoBox/{toDoBoxId}")
    public SingleResponse<Long> createToDoBoxObj(@PathVariable Long toDoBoxId) {
        return responseService.returnSingleResponse(toDoElmService.createToDoElmObj(toDoBoxId));
    }

    /**
     * todoelm 내용 수정
     */
    @PutMapping("/user/toDoElm/{toDoElmId}")
    public Response updateToDoElmContent(@PathVariable Long toDoElmId, @RequestBody ToDoElmParam param) {
        if(toDoElmService.updateToDoElmContent(toDoElmId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }

    /**
     * todoElm completed여부 수정
     */
    @PatchMapping("/user/toDoElm/{toDoElmId}")
    public SingleResponse<Boolean> updateToDoElmCompletedYn(@PathVariable Long toDoElmId) {
        return responseService.returnSingleResponse(toDoElmService.updateToDoElmCompletedYn(toDoElmId));
    }

    /**
     * todoelm 삭제
     */
    @DeleteMapping("/user/toDoElm/{toDoElmId}")
    public Response deleteToDoElmContent(@PathVariable Long toDoElmId) {
        toDoElmService.deleteToDoElm(toDoElmId);
        return responseService.returnSuccessResponse();
    }

}
