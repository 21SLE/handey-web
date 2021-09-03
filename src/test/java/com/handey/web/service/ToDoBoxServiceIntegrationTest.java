package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.home.ToDoBoxRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
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
    @Commit // db에 반영됨
    void createToDoBox() {
        //given
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setTitle("homework");

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setTitle("laundry");

        ToDoBox toDoBox3 = new ToDoBox();
        toDoBox3.setTitle("work");

        //when
        Long saveId1 = toDoBoxService.createToDoBox(toDoBox1);
        Long saveId2 = toDoBoxService.createToDoBox(toDoBox2);
        Long saveId3 = toDoBoxService.createToDoBox(toDoBox3);

        //then
        ToDoBox findMember1 = toDoBoxService.findOneToDoBox(saveId1).get();
        ToDoBox findMember2 = toDoBoxService.findOneToDoBox(saveId2).get();
        ToDoBox findMember3 = toDoBoxService.findOneToDoBox(saveId3).get();
        assertThat(toDoBox1.getTitle()).isEqualTo(findMember1.getTitle());
        assertThat(toDoBox2.getTitle()).isEqualTo(findMember2.getTitle());
        assertThat(toDoBox3.getTitle()).isEqualTo(findMember3.getTitle());


    }

}