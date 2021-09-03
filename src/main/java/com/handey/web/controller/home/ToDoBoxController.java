package com.handey.web.controller.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.service.ToDoBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoBoxController {
    private final ToDoBoxService toDoBoxService;

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoBoxController(ToDoBoxService toDoBoxService) {
        this.toDoBoxService = toDoBoxService;
    }

    /**
     * 투두 박스 리스트 조회
     */
    @GetMapping("/toDoBoxList")
    public List<ToDoBox> getToDoBoxList() {
        return toDoBoxService.getToDoBoxList();
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
