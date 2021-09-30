package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;

import java.util.List;
import java.util.Optional;

public interface ToDoBoxRepository {
    ToDoBox save(ToDoBox toDoBox);
    Optional<ToDoBox> findById(Long id);
    List<ToDoBox> findAll();
    List<ToDoBox> findAllByUserId(Long userId);
    void deleteById(Long id);
}
