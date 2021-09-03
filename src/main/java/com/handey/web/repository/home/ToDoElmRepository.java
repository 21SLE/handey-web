package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;

import java.util.List;
import java.util.Optional;

public interface ToDoElmRepository {
    ToDoElm save(ToDoBox toDoBox, ToDoElm ToDoElm);
    Optional<ToDoElm> findById(Long id);
    List<ToDoElm> findByToDoBoxId(Long toDoBoxId);
    List<ToDoElm> findAll();
    void deleteById(Long id);
}
