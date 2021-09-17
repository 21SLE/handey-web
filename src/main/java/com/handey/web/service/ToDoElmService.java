package com.handey.web.service;

import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.controller.home.ToDoBoxParam;
import com.handey.web.controller.home.ToDoElmParam;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.repository.home.ToDoBoxRepository;
import com.handey.web.repository.home.ToDoElmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToDoElmService {
    private final ToDoElmRepository toDoElmRepository;
    private final ToDoBoxRepository toDoBoxRepository;


    public ToDoElmService(ToDoElmRepository toDoElmRepository, ToDoBoxRepository toDoBoxRepository) {
        this.toDoElmRepository = toDoElmRepository;
        this.toDoBoxRepository = toDoBoxRepository;
    }

    /**
     * ToDoElm 생성
     */
    public Long createToDoElm(Long toDoBoxId, ToDoElm toDoElm) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElmRepository.save(toDoBox, toDoElm);
        return toDoElm.getId();
    }

    /**
     * ToDoElm 객체 생성
     */
    public Long createToDoElmObj(Long toDoBoxId) {
        ToDoElm toDoElm = new ToDoElm();
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElmRepository.save(toDoBox, toDoElm);
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

    /**
     * 투두 element 내용 수정
     */
    public boolean updateToDoElmContent(Long toDoElmId, ToDoElmParam param) {
        ToDoElm toDoElm = toDoElmRepository.findById(toDoElmId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElm.updateContent(param.getContent());
        return true;
    }

    /**
     * 투두 element completedYN 변경
     */
    public boolean updateToDoElmCompletedYn(Long toDoElmId) {
        ToDoElm toDoElm = toDoElmRepository.findById(toDoElmId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElm.updateCompletedYn(!toDoElm.isCompleted());
        return toDoElm.isCompleted();
    }

    /**
     * 투두 element 삭제
     */
    public void deleteToDoElm(Long toDoElmId) {
        ToDoElm toDoElm = toDoElmRepository.findById(toDoElmId).orElseThrow(ToDoNoDataFoundException::new);
        toDoElmRepository.deleteById(toDoElmId);
    }
}
