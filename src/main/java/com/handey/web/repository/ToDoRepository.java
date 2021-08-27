package com.handey.web.repository;

import com.handey.web.domain.ToDoBox;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
    ToDoBox save(ToDoBox toDoBox);
    Optional<ToDoBox> findById(Long toDoBoxId);
    Optional<ToDoBox> findByName(String toDoBoxTitle);
    List<ToDoBox> findAll();

}
