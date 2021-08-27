package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.home.ToDoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
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
        return toDoBox.getId();
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
    public Optional<ToDoBox> findOneToDoBox(Long id) {
        return toDoRepository.findById(id);
    }

}
