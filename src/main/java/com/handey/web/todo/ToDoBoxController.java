package com.handey.web.todo;

import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import com.handey.web.trash.TrashBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoBoxController {
    private final ToDoBoxService toDoBoxService;
    private final ToDoElmService toDoElmService;
    private final TrashBoxService trashBoxService;
    private final ResponseService responseService;

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoBoxController(ToDoBoxService toDoBoxService, ToDoElmService toDoElmService, TrashBoxService trashBoxService, ResponseService responseService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoElmService = toDoElmService;
        this.trashBoxService = trashBoxService;
        this.responseService = responseService;
    }

    /**
     * 투두 박스 리스트 전체 조회
     */
    @GetMapping("/toDoBoxList")
    public ListResponse<ToDoBox> getToDoBoxList() {
        return responseService.returnListResponse(toDoBoxService.getToDoBoxList());
    }

    /**
     * 투두 박스 리스트 조회 by userId
     */
    @GetMapping("/user/{userId}/toDoBoxList")
    public ListResponse<ToDoBox> getToDoBoxListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(toDoBoxService.getToDoBoxListByUserId(userId));
    }

    /**
     * 투두 박스 객체 생성
     */
    @PostMapping("/user/{userId}/toDoBox")
    public SingleResponse<Long> createToDoBoxObj(@PathVariable Long userId) {
        return responseService.returnSingleResponse(toDoBoxService.createToDoBoxObj(userId));
    }

    /**
     * 투두 박스 타이틀 수정
     */
    @PutMapping("/user/toDoBox/{toDoBoxId}")
    public Response updateToDoBoxTitle(@PathVariable Long toDoBoxId, @RequestBody ToDoBoxParam param) {
        if(toDoBoxService.updateToDoBoxTitle(toDoBoxId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }

    /**
     * 투두 박스 타이틀 유무 변경
     */
    @PatchMapping("/user/toDoBox/{toDoBoxId}/title")
    public SingleResponse<Boolean> updateToDoBoxNoTitleYn(@PathVariable Long toDoBoxId) {
        return responseService.returnSingleResponse(toDoBoxService.updateToDoBoxNoTitleYn(toDoBoxId));
    }

    /**
     * 투두 박스 고정상태 수정
     */
    @PatchMapping("/user/toDoBox/{toDoBoxId}")
    public SingleResponse<Boolean> updateToDoBoxFixedYn(@PathVariable Long toDoBoxId) {
        return responseService.returnSingleResponse(toDoBoxService.updateToDoBoxFixedYn(toDoBoxId));
    }

    /**
     * 투두 박스 삭제
     */
    @DeleteMapping("user/{userId}/toDoBox/{toDoBoxId}")
    @Transactional
    public Response deleteToDoBox(@PathVariable Long userId, @PathVariable Long toDoBoxId) {
        // 투두 박스 삭제시 휴지통으로 이동
        trashBoxService.createTrashBox(userId, toDoBoxService.findOneToDoBox(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new));
        toDoBoxService.deleteToDoBox(toDoBoxId);
        return responseService.returnSuccessResponse();
    }

    /**
     * create toDoBox with todoElms only for tests
     */
    @PostMapping("/user/{userId}/toDo")
    public SingleResponse<Long> createToDoBox(@PathVariable Long userId, @RequestBody ToDoParam param) {
        Long toDoBoxId = toDoBoxService.createToDoBoxObj(userId);
        ToDoBoxParam toDoBoxParam = new ToDoBoxParam();
        toDoBoxParam.setTitle(param.getTitle());
        toDoBoxParam.setFixed(param.isFixed());
        toDoBoxService.updateToDoBoxTitle(toDoBoxId, toDoBoxParam);
        toDoBoxService.updateToDoBoxFixedYn(toDoBoxId);
        param.getToDoElmList().forEach(toDoElm -> {
            Long toDoElmId = toDoElmService.createToDoElmObj(toDoBoxId);
            ToDoElmParam toDoElmParam = new ToDoElmParam();
            toDoElmParam.setContent(toDoElm.getContent());
            toDoElmService.updateToDoElmContent(toDoElmId, toDoElmParam);
            if(toDoElm.isCompleted())
                toDoElmService.updateToDoElmCompletedYn(toDoElmId);
        });
        return responseService.returnSingleResponse(toDoBoxId);
    }
}
