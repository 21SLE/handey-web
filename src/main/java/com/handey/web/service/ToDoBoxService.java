package com.handey.web.service;

import com.handey.web.controller.home.ToDoBoxParam;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.domain.trash.TrashBox;
import com.handey.web.repository.home.ToDoBoxRepository;
import com.handey.web.repository.home.ToDoElmRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ToDoBoxService {

    private final ToDoBoxRepository toDoBoxRepository;
    private final ToDoElmRepository toDoElmRepository;

    public ToDoBoxService(ToDoBoxRepository toDoBoxRepository, ToDoElmRepository toDoElmRepository) {
        this.toDoBoxRepository = toDoBoxRepository;
        // 외부에서 넣어줌(dependency injection)
        this.toDoElmRepository = toDoElmRepository;
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

    /**
     * 투두박스 타이틀 유무 변경
     */
    public boolean updateToDoBoxNoTitleYn(Long toDoBoxId) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBox.updateNoTitle(!toDoBox.isNoTitle());
        return toDoBox.isNoTitle();
    }

    /**
     * 투두박스 고정 상태 변경
     */
    public boolean updateToDoBoxFixedYn(Long toDoBoxId) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBox.updateFixedYn(!toDoBox.isFixed());
        return toDoBox.isFixed();
    }

    /**
     * 투두박스 삭제
     */
    public void deleteToDoBox(Long toDoBoxId) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBoxRepository.deleteById(toDoBoxId);
    }

    /**
     * 휴지통 -> 투두
     */
    public Long restoreToDo(TrashBox trashBox) {
        ToDoBox toDoBox = new ToDoBox();

        toDoBox.setTitle(trashBox.getTitle());
        toDoBox.setNoTitle(trashBox.isNoTitle());

        trashBox.getTrashElmList().forEach(trashElm -> {
            ToDoElm toDoElm = new ToDoElm();

            toDoElm.setCompleted(trashElm.isCompleted());
            toDoElm.setContent(trashElm.getContent());

            toDoElmRepository.save(toDoBox, toDoElm);
        });

        toDoBoxRepository.save(toDoBox);
        return toDoBox.getId();
    }
}
