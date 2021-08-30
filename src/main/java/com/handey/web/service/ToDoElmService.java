package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.repository.home.ToDoElmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToDoElmService {
    private final ToDoElmRepository toDoElmRepository;


    public ToDoElmService(ToDoElmRepository toDoElmRepository) {
        this.toDoElmRepository = toDoElmRepository;
    }

    /**
     * ToDoElm 생성
     */
    public Long createToDoElm(ToDoElm toDoElm) {

        toDoElmRepository.save(toDoElm);
        return toDoElm.getId();
    }

    /**
     * 한 ToDoBox 내 ToDoElm 리스트 조회
     */
    public List<ToDoElm> getToDoElmList(Long toDoBoxId) {
        return toDoElmRepository.findByToDoBoxId(toDoBoxId);
    }

    /**
     * ToDoElm 단건 조회
     */
    public Optional<ToDoElm> findOneToDoElm(Long id) {
        return toDoElmRepository.findById(id);
    }

    /**
     * ToDoElm 전체 조회
     */
    public List<ToDoElm> getAllToDoElm() {
        return toDoElmRepository.findAll();
    }
}
