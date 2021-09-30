package com.handey.web.repository.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;

import java.util.List;
import java.util.Optional;

public interface ToDoElmHstRepository {
    ToDoElmHst save(ToDoElmHst toDoElmHst);
    Optional<ToDoElmHst> findById(Long id);
    List<ToDoElmHst> findByToDoBoxHstId(String toDoBoxHstId);
    List<ToDoElmHst> findAll();
    void deleteById(Long id);
}
