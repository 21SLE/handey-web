package com.handey.web.service;

import com.handey.web.domain.ToDoBox;
import com.handey.web.repository.MemoryToDoRepository;
import com.handey.web.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional  // afterEach에서 clearstore라고 생각하면 됨
class ToDoServiceIntegrationTest {

    @Autowired ToDoService toDoService;
    @Autowired ToDoRepository toDoRepository;

    @Test
    void createToDoBox() {
        //given
        ToDoBox toDoBox = new ToDoBox();
        toDoBox.setToDoBoxTitle("homework");

        //when
        Long saveId = toDoService.createToDoBox(toDoBox);

        //then
        ToDoBox findMember = toDoService.findOneToDoBox(saveId).get();
        assertThat(toDoBox.getToDoBoxTitle()).isEqualTo(findMember.getToDoBoxTitle());

    }

}