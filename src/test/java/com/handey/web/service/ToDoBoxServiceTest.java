package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.home.MemoryToDoBoxRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ToDoBoxServiceTest {

    ToDoBoxService toDoBoxService;
    MemoryToDoBoxRepository toDoRepository;

    @BeforeEach
    public void beforeEach() {
        toDoRepository = new MemoryToDoBoxRepository();
        toDoBoxService = new ToDoBoxService(toDoRepository);
    }

    @AfterEach
    public void afterEach() {
        toDoRepository.clearStore();
    }

    @Test
    void createToDoBox() {
        //given
        ToDoBox toDoBox = new ToDoBox();
        toDoBox.setTitle("homework");

        //when
        Long saveId = toDoBoxService.createToDoBox(toDoBox);

        //then
        ToDoBox findMember = toDoBoxService.findOneToDoBox(saveId).get();
        assertThat(toDoBox.getTitle()).isEqualTo(findMember.getTitle());

    }

    @Test
    void getToDoBoxList() {
    }

    @Test
    void findOneToDoBox() {
    }
}