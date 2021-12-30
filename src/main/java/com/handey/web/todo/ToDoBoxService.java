package com.handey.web.todo;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.trash.TrashBox;
import com.handey.web.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToDoBoxService {

    private final ToDoBoxRepository toDoBoxRepository;
    private final ToDoElmRepository toDoElmRepository;
    private final MemberRepository memberRepository;

    public ToDoBoxService(ToDoBoxRepository toDoBoxRepository, ToDoElmRepository toDoElmRepository, MemberRepository memberRepository) {
        this.toDoBoxRepository = toDoBoxRepository;
        // 외부에서 넣어줌(dependency injection)
        this.toDoElmRepository = toDoElmRepository;
        this.memberRepository = memberRepository;
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
    public Optional<ToDoBox> createToDoBoxObj(Long userId) {
        ToDoBox toDoBox = new ToDoBox();
        toDoBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        toDoBox.setIndex(((long) toDoBoxRepository.findAllByUserId(userId).size()));
        toDoBoxRepository.save(toDoBox);
        ToDoElm toDoElm = new ToDoElm();
        toDoElmRepository.save(toDoBox, toDoElm);
        List<ToDoElm> newToDoElmList = new ArrayList<>(List.of(toDoElm));
        toDoBox.updateToDoElmList(newToDoElmList);
        return Optional.ofNullable(toDoBox);
    }

    /**
     * 투두박스 리스트 조회
     */
    public List<ToDoBox> getToDoBoxList() {
        return toDoBoxRepository.findAll();
    }

    /**
     * 투두박스 리스트 조회 by userId
     */
    public List<ToDoBox> getToDoBoxListByUserId(Long userId) {
        return toDoBoxRepository.findAllByUserId(userId);
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
//    public boolean updateToDoBoxNoTitleYn(Long toDoBoxId) {
//        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
//        toDoBox.updateNoTitle(!toDoBox.isNoTitle());
//        return toDoBox.isNoTitle();
//    }

    /**
     * 투두박스 고정 상태 변경
     */
    public boolean updateToDoBoxFixedYn(Long toDoBoxId) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBox.updateFixedYn(!toDoBox.isFixed());
        return toDoBox.isFixed();
    }

    /**
     * 투두박스 index 수정
     */
    public boolean updateToDoBoxIndex(Long toDoBoxId, Long newIndex) {
        ToDoBox toDoBox = toDoBoxRepository.findById(toDoBoxId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBox.updateIndex(newIndex);
        return true;
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
    public Long restoreToDo(Long userId, TrashBox trashBox) {
        ToDoBox toDoBox = new ToDoBox();

        toDoBox.setTitle(trashBox.getTitle());
//        toDoBox.setNoTitle(trashBox.isNoTitle());
        toDoBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        toDoBoxRepository.save(toDoBox);

        trashBox.getTrashElmList().forEach(trashElm -> {
            ToDoElm toDoElm = new ToDoElm();

            toDoElm.setCompleted(trashElm.isCompleted());
            toDoElm.setContent(trashElm.getContent());

            toDoElmRepository.save(toDoBox, toDoElm);
        });

        return toDoBox.getId();
    }
}
