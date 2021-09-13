package com.handey.web.controller.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.service.ToDoBoxService;
import com.handey.web.service.ToDoElmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoBoxController {
    private final ToDoBoxService toDoBoxService;
    private final ToDoElmService toDoElmService;

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoBoxController(ToDoBoxService toDoBoxService, ToDoElmService toDoElmService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoElmService = toDoElmService;
    }

    /**
     * 투두 박스 리스트 조회
     */
    @GetMapping("/toDoBoxList")
    public List<ToDoBox> getToDoBoxList() {
        return toDoBoxService.getToDoBoxList();
    }

    /**
     * create toDoBox with todoElms only for tests
     */
    @PostMapping("/toDo")
    public Long createToDoBox(@RequestBody ToDoParam param) {
        Long toDoBoxId = toDoBoxService.createToDoBoxObj();
        ToDoBoxParam toDoBoxParam = new ToDoBoxParam();
        toDoBoxParam.setTitle(param.getTitle());
        toDoBoxParam.setFixed(param.isFixed());
        toDoBoxService.updateToDoBoxTitle(toDoBoxId, toDoBoxParam);
        param.getToDoElmList().forEach(toDoElm -> {
            Long toDoElmId = toDoElmService.createToDoElmObj(toDoBoxId);
            ToDoElmParam toDoElmParam = new ToDoElmParam();
            toDoElmParam.setContent(toDoElm.getContent());
            toDoElmParam.setCompleted(toDoElm.isCompleted());
            toDoElmService.updateToDoElmContent(toDoElmId, toDoElmParam);
        });
        return toDoBoxId;
    }

    /**
     * 투두 박스 객체 생성
     */
    @PostMapping("/toDoBox")
    public Long createToDoBoxObj() {
        return toDoBoxService.createToDoBoxObj();
    }

    /**
     * 투두 박스 타이틀 수정
     */
    @PutMapping("/toDoBox/{toDoBoxId}")
    public boolean updateToDoBoxTitle(@PathVariable Long toDoBoxId, @RequestBody ToDoBoxParam param) {
        return toDoBoxService.updateToDoBoxTitle(toDoBoxId, param);
    }

    /**
     * 투두 박스 삭제
     */
    @DeleteMapping("/toDoBox/{toDoBoxId}")
    public boolean deleteToDoBox(@PathVariable Long toDoBoxId) {
        toDoBoxService.deleteToDoBox(toDoBoxId);
        return true;
    }
}
