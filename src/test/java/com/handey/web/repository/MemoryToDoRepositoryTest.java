package com.handey.web.repository;

import com.handey.web.domain.ToDoBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryToDoRepositoryTest {
    MemoryToDoRepository repository = new MemoryToDoRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        ToDoBox toDoBox = new ToDoBox();
        toDoBox.setToDoBoxTitle("do the hw");

        repository.save(toDoBox);

        ToDoBox result = repository.findById(toDoBox.getToDoBoxId()).get();
        assertThat(toDoBox).isEqualTo(result);
    }

    @Test
    public void findByName() {
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setToDoBoxTitle("do the eng hw");
        repository.save(toDoBox1);

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setToDoBoxTitle("do the programming hw");
        repository.save(toDoBox2);

        ToDoBox result = repository.findByName("do the eng hw").get();

        assertThat(result).isEqualTo(toDoBox1);
    }

    @Test
    public void findAll() {
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setToDoBoxTitle("do the eng hw");
        repository.save(toDoBox1);

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setToDoBoxTitle("do the programming hw");
        repository.save(toDoBox2);

        List<ToDoBox> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}