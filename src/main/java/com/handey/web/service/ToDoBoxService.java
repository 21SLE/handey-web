package com.handey.web.service;

import com.handey.web.controller.home.ToDoBoxParam;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.repository.home.ToDoBoxRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ToDoBoxService {

    private final ToDoBoxRepository toDoBoxRepository;

    public ToDoBoxService(ToDoBoxRepository toDoBoxRepository) {
        this.toDoBoxRepository = toDoBoxRepository;
        // 외부에서 넣어줌(dependency injection)
    }

    /**
     * 투두박스 생성
     */
    public Long createToDoBox(ToDoBox toDoBox) {
        toDoBoxRepository.save(toDoBox);
        return toDoBox.getId();
    }

    /**
     * 투두박스 객체 생성
     */
    public Long createToDoBoxObj() {
        ToDoBox toDoBox = new ToDoBox();
        toDoBoxRepository.save(toDoBox);
        return toDoBox.getId();
    }

    /**
     * 투두박스 리스트 조회
     */
    public List<ToDoBox> getToDoBoxList() {
        return toDoBoxRepository.findAll();
    }

    /**
     * 투두박스 단건 조회
     */
    public Optional<ToDoBox> findOneToDoBox(Long id) {
        return toDoBoxRepository.findById(id);
    }

    /**
     * 투두박스 타이틀 수정
     */
    public boolean updateToDoBoxTitle(Long toDoBoxId, ToDoBoxParam param) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBox.updateTitle(param.getTitle());
        return true;
    }
}
