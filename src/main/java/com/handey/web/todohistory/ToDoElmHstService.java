package com.handey.web.todohistory;


import com.handey.web.common.exception.ToDoNoDataFoundException;
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
