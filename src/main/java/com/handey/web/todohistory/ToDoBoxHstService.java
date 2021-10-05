package com.handey.web.todohistory;


import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.todo.ToDoBox;
import com.handey.web.todo.ToDoElm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Transactional
@Service
public class ToDoBoxHstService {
    private final ToDoBoxHstRepository toDoBoxHstRepository;
    private final ToDoElmHstRepository toDoElmHstRepository;

    public ToDoBoxHstService(ToDoBoxHstRepository toDoBoxHstRepository, ToDoElmHstRepository toDoElmHstRepository) {
        this.toDoBoxHstRepository = toDoBoxHstRepository;
        this.toDoElmHstRepository = toDoElmHstRepository;
    }

    public boolean createToDoBoxHst(Member member, ToDoBox toDoBox) {
        ToDoBoxHst toDoBoxHst = new ToDoBoxHst();

        // 어제 날짜로 저장
        toDoBoxHst.setSaveDt(LocalDate.now().minus(Period.ofDays(1)));
        toDoBoxHst.setTitle(toDoBox.getTitle());

        toDoBoxHst.setMember(member);
        toDoBoxHstRepository.save(toDoBoxHst);

        List<ToDoElm> toDoElmList = toDoBox.getToDoElmList();
        AtomicBoolean allToDoElmCompleted = new AtomicBoolean(true);

        toDoElmList.forEach(toDoElm -> {
            if(!toDoElm.isCompleted()) allToDoElmCompleted.set(false);

            ToDoElmHst toDoElmHst = new ToDoElmHst();
            toDoElmHst.setToDoBoxHst(toDoBoxHst);
            toDoElmHst.setContent(toDoElm.getContent());

            toDoElmHstRepository.save(toDoElmHst);
        });

        return allToDoElmCompleted.get();
    }

    public List<ToDoBoxHst> getToDoBoxHstList() {
        return toDoBoxHstRepository.findAll();
    }

    public void deleteToDoBoxHst(Long toDoBoxHstId) {
        ToDoBoxHst toDoBoxHst = toDoBoxHstRepository.findById(toDoBoxHstId).orElseThrow(ToDoNoDataFoundException::new);
        toDoBoxHstRepository.deleteById(toDoBoxHstId);
    }

    public List<ToDoBoxHst> getToDoBoxHstListByDate(LocalDate searchDt) {
        return toDoBoxHstRepository.findByDate(searchDt);
    }

    /**
     * 회원 투두 히스토리 박스 전체 조회
     */
    public List<ToDoBoxHst> getToDoBoxHstListByUserId(Long userId) {
        return toDoBoxHstRepository.findByUserId(userId);
    }

    /**
     * 회원 투두 히스토리 박스 날짜별 조회
     */
    public List<ToDoBoxHst> getToDoBoxHstListByUserIdAndDate(Long userId, LocalDate searchDt) {
        return toDoBoxHstRepository.findByUserIdAndDate(userId, searchDt);
    }
}