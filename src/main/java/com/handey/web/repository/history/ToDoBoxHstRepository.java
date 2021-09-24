package com.handey.web.repository.history;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.home.ToDoBox;

import java.util.List;
import java.util.Optional;

public interface ToDoBoxHstRepository {
    ToDoBoxHst save(ToDoBoxHst ToDoBoxHst);
    Optional<ToDoBoxHst> findById(Long id);
    List<ToDoBoxHst> findByDate(String saveDt);
    List<ToDoBoxHst> findAll();
    void deleteById(Long id);
}
