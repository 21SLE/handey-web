package com.handey.web.todo;

import com.handey.web.common.exception.ToDoNoDataFoundException;
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

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoBoxController(ToDoBoxService toDoBoxService, ToDoElmService toDoElmService, TrashBoxService trashBoxService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoElmService = toDoElmService;
        this.trashBoxService = trashBoxService;
    }

    /**
     * 투두 박스 리스트 전체 조회
     */
    @GetMapping("/toDoBoxList")
    public List<ToDoBox> getToDoBoxList() {
        return toDoBoxService.getToDoBoxList();
    }

    /**
     * 투두 박스 리스트 조회 by userId
     */
    @GetMapping("/user/{userId}/toDoBoxList")
    public List<ToDoBox> getToDoBoxListByUserId(@PathVariable Long userId) {
        return toDoBoxService.getToDoBoxListByUserId(userId);
    }

    /**
     * 투두 박스 객체 생성
     */
    @PostMapping("/user/{userId}/toDoBox")
    public Long createToDoBoxObj(@PathVariable Long userId) {
        return toDoBoxService.createToDoBoxObj(userId);
    }

    /**
     * 투두 박스 타이틀 수정
     */
    @PutMapping("/user/toDoBox/{toDoBoxId}")
    public boolean updateToDoBoxTitle(@PathVariable Long toDoBoxId, @RequestBody ToDoBoxParam param) {
        return toDoBoxService.updateToDoBoxTitle(toDoBoxId, param);
    }

    /**
     * 투두 박스 타이틀 유무 변경
     */
    @PatchMapping("/user/toDoBox/{toDoBoxId}/title")
    public boolean updateToDoBoxNoTitleYn(@PathVariable Long toDoBoxId) {
        return toDoBoxService.updateToDoBoxNoTitleYn(toDoBoxId);
    }

    /**
     * 투두 박스 고정상태 수정
     */
    @PatchMapping("/user/toDoBox/{toDoBoxId}")
    public boolean updateToDoBoxFixedYn(@PathVariable Long toDoBoxId) {
        return toDoBoxService.updateToDoBoxFixedYn(toDoBoxId);
    }

    /**
     * 투두 박스 삭제
     */
    @DeleteMapping("user/{userId}/toDoBox/{toDoBoxId}")
    @Transactional
    public boolean deleteToDoBox(@PathVariable Long userId, @PathVariable Long toDoBoxId) {
        // 투두 박스 삭제시 휴지통으로 이동
        trashBoxService.createTrashBox(userId, toDoBoxService.findOneToDoBox(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new));
        toDoBoxService.deleteToDoBox(toDoBoxId);
        return true;
    }

    /**
     * create toDoBox with todoElms only for tests
     */
    @PostMapping("/user/{userId}/toDo")
    public Long createToDoBox(@PathVariable Long userId, @RequestBody ToDoParam param) {
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
        return toDoBoxId;
    }
}
