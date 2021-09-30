package com.handey.web.service;


import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.repository.history.ToDoBoxHstRepository;
import com.handey.web.repository.history.ToDoElmHstRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ToDoElmHstService {
    private final ToDoElmHstRepository toDoElmHstRepository;

    public ToDoElmHstService(ToDoElmHstRepository toDoElmHstRepository) {
        this.toDoElmHstRepository = toDoElmHstRepository;
    }

    public Long createToElmHst(ToDoElmHst toDoElmHst) {
        toDoElmHstRepository.save(toDoElmHst);
        return toDoElmHst.getId();
    }

    public List<ToDoElmHst> getToDoElmHstList() {
        return toDoElmHstRepository.findAll();
    }

    public void deleteToDoElmHst(Long toDoElmHstId) {
        ToDoElmHst toDoElmHst = toDoElmHstRepository.findById(toDoElmHstId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElmHstRepository.deleteById(toDoElmHstId);
    }
}
