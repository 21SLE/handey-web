package com.handey.web.service;

import com.handey.web.domain.ToDoBox;
import com.handey.web.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
        // 외부에서 넣어줌(dependency injection)
    }

    /**
     * 투두박스 생성
     */
    public Long createToDoBox(ToDoBox toDoBox) {
        toDoRepository.save(toDoBox);
        return toDoBox.getToDoBoxId();
    }

    /**
     * 투두박스 리스트 조회
     */
    public List<ToDoBox> findToDoBoxes() {
        return toDoRepository.findAll();
    }

    /**
     * 투두박스 단건 조회
     */
    public Optional<ToDoBox> findOneToDoBox(Long toDoBoxId) {
        return toDoRepository.findById(toDoBoxId);
    }

}
