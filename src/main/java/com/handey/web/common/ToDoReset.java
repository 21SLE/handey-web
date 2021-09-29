package com.handey.web.common;

import com.handey.web.service.ToDoBoxHstService;
import com.handey.web.service.ToDoBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ToDoReset {
    private final ToDoBoxService toDoBoxService;
    private final ToDoBoxHstService toDoBoxHstService;

    @Autowired
    public ToDoReset(ToDoBoxService toDoBoxService, ToDoBoxHstService toDoBoxHstService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoBoxHstService = toDoBoxHstService;
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
    @Scheduled(fixedDelay=1000000)
    @Transactional
    public void resetToDo(){
        System.out.println("스케줄링 테스트");
        toDoBoxService.getToDoBoxList().forEach(toDoBox -> {
            // history table로 복사
            boolean doesToDoBoxNeedToBeDeleted = toDoBoxHstService.createToDoBoxHst(toDoBox);

            // todoelm 모두 completed이면 todobox 삭제
            if(doesToDoBoxNeedToBeDeleted) {
                toDoBoxService.deleteToDoBox(toDoBox.getId());
            }
        });
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