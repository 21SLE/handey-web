package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.home.MemoryToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ToDoServiceTest {

    ToDoService toDoService;
    MemoryToDoRepository toDoRepository;

    @BeforeEach
    public void beforeEach() {
        toDoRepository = new MemoryToDoRepository();
        toDoService = new ToDoService(toDoRepository);
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
        Long saveId = toDoService.createToDoBox(toDoBox);

        //then
        ToDoBox findMember = toDoService.findOneToDoBox(saveId).get();
        assertThat(toDoBox.getTitle()).isEqualTo(findMember.getTitle());

    }

    @Test
    void findToDoBoxes() {
    }

    @Test
    void findOneToDoBox() {
    }
}