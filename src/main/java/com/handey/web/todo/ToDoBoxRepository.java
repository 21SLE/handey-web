package com.handey.web.todo;

import java.util.List;
import java.util.Optional;

public interface ToDoBoxRepository {
    ToDoBox save(ToDoBox toDoBox);
    Optional<ToDoBox> findById(Long id);
    List<ToDoBox> findAll();
    List<ToDoBox> findAllByUserId(Long userId);
    void deleteById(Long id);
}
