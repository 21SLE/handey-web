package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.repository.home.ToDoElmRepository;
import com.handey.web.repository.home.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ToDoElmServiceIntegrationTest {

    @Autowired ToDoElmService toDoElmService;
    @Autowired ToDoElmRepository toDoElmRepository;
    @Autowired ToDoService toDoService;
    @Autowired ToDoRepository toDoRepository;

    @Test
    @Commit
    void createToDoElm() {
        //given
        //양방향 연관관계에서 주인에만 데이터를 입력하는 것. 주인은 다. 여기서 주인은 toDoElm
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setTitle("toDoBoxTest 1");
        toDoService.createToDoBox(toDoBox1);

        ToDoElm toDoElm1 = new ToDoElm();
        toDoElm1.setContent("first todo");
        toDoElm1.setToDoBox(toDoBox1);
        toDoBox1.getToDoElmList().add(toDoElm1);
//        System.out.println(toDoElm1.getId());

        ToDoElm toDoElm2 = new ToDoElm();
        toDoElm2.setContent("2nd todo");
        toDoElm2.setToDoBox(toDoBox1);
        toDoBox1.getToDoElmList().add(toDoElm2);

        //when
        Long saveId1 = toDoElmService.createToDoElm(toDoElm1);
        Long saveId2 = toDoElmService.createToDoElm(toDoElm2);

        //then
        ToDoElm findToDoElm1 = toDoElmService.findOneToDoElm(saveId1).get();
        ToDoElm findToDoElm2 = toDoElmService.findOneToDoElm(saveId2).get();
        assertThat(toDoElm1.getContent()).isEqualTo(findToDoElm1.getContent());
        assertThat(toDoElm2.getContent()).isEqualTo(findToDoElm2.getContent());

    }

    @Test
    void getToDoElmList() {
    }

    @Test
    void findOneToDoElm() {
    }

    @Test
    void getAllToDoElm() {
    }
}