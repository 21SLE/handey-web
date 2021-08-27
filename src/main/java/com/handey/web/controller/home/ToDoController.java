package com.handey.web.controller.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {
    private final ToDoService toDoService;

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/toDoBoxList")
    public List<ToDoBox> getToDoBoxList() {
        return toDoService.getToDoBoxList();
    }
}
