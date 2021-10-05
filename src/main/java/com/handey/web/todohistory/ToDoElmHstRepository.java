package com.handey.web.todohistory;

import java.util.List;
import java.util.Optional;

public interface ToDoElmHstRepository {
    ToDoElmHst save(ToDoElmHst toDoElmHst);
    Optional<ToDoElmHst> findById(Long id);
    List<ToDoElmHst> findByToDoBoxHstId(String toDoBoxHstId);
    List<ToDoElmHst> findAll();
    void deleteById(Long id);
}
