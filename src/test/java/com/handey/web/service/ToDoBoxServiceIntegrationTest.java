package com.handey.web.service;

import com.handey.web.todo.ToDoBoxRepository;
import com.handey.web.todo.ToDoBoxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional  // afterEach에서 clearstore라고 생각하면 됨
class ToDoBoxServiceIntegrationTest {

    @Autowired
    ToDoBoxService toDoBoxService;
    @Autowired
    ToDoBoxRepository toDoBoxRepository;

    @Test
    void createToDoBox() {


    }

}