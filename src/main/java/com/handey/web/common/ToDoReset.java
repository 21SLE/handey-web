package com.handey.web.common;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.service.ToDoBoxHstService;
import com.handey.web.service.ToDoBoxService;
import com.handey.web.service.ToDoElmHstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ToDoReset {
    private final ToDoBoxService toDoBoxService;
    private final ToDoBoxHstService toDoBoxHstService;
    private final ToDoElmHstService toDoElmHstService;

    @Autowired
    public ToDoReset(ToDoBoxService toDoBoxService, ToDoBoxHstService toDoBoxHstService, ToDoElmHstService toDoElmHstService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoBoxHstService = toDoBoxHstService;
        this.toDoElmHstService = toDoElmHstService;
    }

    // 초, 분, 시, 일, 월, 요일
    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void resetToDoAt00() {
        resetToDo();
    }

    @Scheduled(cron="0 0 01 * * *")
    @Transactional
    public void resetToDoAt01() {

    }

    @Scheduled(cron="0 0 02 * * *")
    @Transactional
    public void resetToDoAt02() {

    }

    // fixedDelay마다 실행 5000 = 5초
//    @Scheduled(fixedDelay=1000000)
    @Transactional
    public void resetToDo(){
        System.out.println("스케줄링 테스트");
        toDoBoxService.getToDoBoxList().forEach(toDoBox -> {
            // history table로 복사
            ToDoBoxHst toDoBoxHst = new ToDoBoxHst();
            toDoBoxHst.setSaveDt(getYesterdayDate());
            toDoBoxHst.setTitle(toDoBox.getTitle());

            Long toDoBoxHstId = toDoBoxHstService.createToDoBoxHst(toDoBoxHst);
            List<ToDoElm> toDoElmList = toDoBox.getToDoElmList();

            toDoElmList.forEach(toDoElm -> {
                ToDoElmHst toDoElmHst = new ToDoElmHst();
                toDoElmHst.setToDoBoxHst(toDoBoxHst);
                toDoElmHst.setContent(toDoElm.getContent());

                toDoElmHstService.createToElmHst(toDoElmHst);
            });

            // todoelm 모두 completed이면 todobox 삭제
            if(checkCompletedAll(toDoElmList)) {
                toDoBoxService.deleteToDoBox(toDoBox.getId());
            }

            System.out.println("toDoBoxHstID: " + toDoBoxHstId);
        });

    }

    public boolean checkCompletedAll(List<ToDoElm> toDoElmList) {
        AtomicBoolean allTrue = new AtomicBoolean(true);
        toDoElmList.forEach(toDoElm -> {
            if(!toDoElm.isCompleted()){
                allTrue.set(false);
            }
        });
        return allTrue.get();
    }

    public LocalDate getYesterdayDate() {
        LocalDate today = LocalDate.now();
        return today.minus(Period.ofDays(1));
    }

}

// 1) 매월 10일 오전 11시
//    0  1  1  10  *  *
//
// 2) 매일 오후 2시 5분 0초
//    0  5  14  *  *  *
//
// 3) 10분마다 도는 스케줄러 : 10분 0초, 20분 0초...
//    0  0/10  *  *  *
//
// 4) 조건에서만 실행되는 스케줄러 : 10분 0초, 11분 0초 ~ 15분 0초까지 실행
//    0  10-15  *  *  *