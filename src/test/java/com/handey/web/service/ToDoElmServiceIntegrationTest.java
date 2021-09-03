package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.repository.home.ToDoElmRepository;
import com.handey.web.repository.home.ToDoBoxRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ToDoElmServiceIntegrationTest {

    @Autowired ToDoElmService toDoElmService;
    @Autowired ToDoElmRepository toDoElmRepository;
    @Autowired
    ToDoBoxService toDoBoxService;
    @Autowired
    ToDoBoxRepository toDoBoxRepository;

    @Test
    @Commit
    void createToDoElm() {
        //given
        //양방향 연관관계에서 주인에만 데이터를 입력하는 것. 주인은 다. 여기서 주인은 toDoElm
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setTitle("toDoBoxTest 1");
        toDoBoxService.createToDoBox(toDoBox1);

        ToDoElm toDoElm1 = new ToDoElm();
        toDoElm1.setContent("first todo");
        toDoElm1.setToDoBox(toDoBox1);
        toDoBox1.getToDoElmList().add(toDoElm1);
//        System.out.println(toDoElm1.getId());

        ToDoElm toDoElm2 = new ToDoElm();
        toDoElm2.setContent("2nd todo");
        toDoElm2.setToDoBox(toDoBox1);
        toDoBox1.getToDoElmList().add(toDoElm2);

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setTitle("투두 박스 테스트 2");
        toDoBoxService.createToDoBox(toDoBox2);

        ToDoElm toDoElm3 = new ToDoElm();
        toDoElm3.setContent("리액트 공부해서 화면 개발하기");
        toDoElm3.setCompleted(true);
        toDoElm3.setToDoBox(toDoBox2);
        toDoBox2.getToDoElmList().add(toDoElm3);
//        System.out.println(toDoElm1.getId());

        ToDoElm toDoElm4 = new ToDoElm();
        toDoElm4.setContent("스프링 공부하기");
        toDoElm4.setCompleted(true);
        toDoElm4.setToDoBox(toDoBox2);
        toDoBox2.getToDoElmList().add(toDoElm4);

        //when
        Long saveId1 = toDoElmService.createToDoElm(toDoBox1.getId(), toDoElm1);
        Long saveId2 = toDoElmService.createToDoElm(toDoBox1.getId(), toDoElm2);
        Long saveId3 = toDoElmService.createToDoElm(toDoBox2.getId(), toDoElm3);
        Long saveId4 = toDoElmService.createToDoElm(toDoBox2.getId(), toDoElm4);

        //then
        ToDoElm findToDoElm1 = toDoElmService.findOneToDoElm(saveId1).get();
        ToDoElm findToDoElm2 = toDoElmService.findOneToDoElm(saveId2).get();
        ToDoElm findToDoElm3 = toDoElmService.findOneToDoElm(saveId3).get();
        ToDoElm findToDoElm4 = toDoElmService.findOneToDoElm(saveId4).get();
        assertThat(toDoElm1.getContent()).isEqualTo(findToDoElm1.getContent());
        assertThat(toDoElm2.getContent()).isEqualTo(findToDoElm2.getContent());
        assertThat(toDoElm3.getContent()).isEqualTo(findToDoElm3.getContent());
        assertThat(toDoElm4.getContent()).isEqualTo(findToDoElm4.getContent());

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