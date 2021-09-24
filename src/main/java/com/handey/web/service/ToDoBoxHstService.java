package com.handey.web.service;


import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.repository.history.ToDoBoxHstRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ToDoBoxHstService {
    private final ToDoBoxHstRepository toDoBoxHstRepository;

    public ToDoBoxHstService(ToDoBoxHstRepository toDoBoxHstRepository) {
        this.toDoBoxHstRepository = toDoBoxHstRepository;
    }

    public Long createToDoBoxHst(ToDoBoxHst toDoBoxHst) {
        toDoBoxHstRepository.save(toDoBoxHst);
        return toDoBoxHst.getId();
    }

    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstRepository.findAll();
    }

}
