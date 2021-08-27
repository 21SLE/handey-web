package com.handey.web.controller;

import com.handey.web.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ToDoController {
    private final ToDoService toDoService;

               // 스프링 빈 등록 2가지 방법 중: 컴포넌트 스캔과 자동 의존관계 생성
    @Autowired // DI(Dependency Injection): Controller -> Service -> Repository
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
}
