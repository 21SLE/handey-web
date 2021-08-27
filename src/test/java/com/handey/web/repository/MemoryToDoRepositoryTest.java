package com.handey.web.repository;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.home.MemoryToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryToDoRepositoryTest {
    MemoryToDoRepository repository = new MemoryToDoRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        ToDoBox toDoBox = new ToDoBox();
        toDoBox.setTitle("do the hw");

        repository.save(toDoBox);

        ToDoBox result = repository.findById(toDoBox.getId()).get();
        assertThat(toDoBox).isEqualTo(result);
    }

    @Test
    public void findByTitle() {
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setTitle("do the eng hw");
        repository.save(toDoBox1);

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setTitle("do the programming hw");
        repository.save(toDoBox2);

        ToDoBox result = repository.findByTitle("do the eng hw").get();

        assertThat(result).isEqualTo(toDoBox1);
    }

    @Test
    public void findAll() {
        ToDoBox toDoBox1 = new ToDoBox();
        toDoBox1.setTitle("do the eng hw");
        repository.save(toDoBox1);

        ToDoBox toDoBox2 = new ToDoBox();
        toDoBox2.setTitle("do the programming hw");
        repository.save(toDoBox2);

        List<ToDoBox> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}