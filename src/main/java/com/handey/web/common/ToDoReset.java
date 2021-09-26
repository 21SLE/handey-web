package com.handey.web.common;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.service.ToDoBoxHstService;
import com.handey.web.service.ToDoBoxService;
import com.handey.web.service.ToDoElmHstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    //5초 마다 실행
    @Scheduled(fixedDelay=5000)
    @Transactional
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
        System.out.println(toDoBoxService.getToDoBoxList());
        toDoBoxService.getToDoBoxList().forEach(toDoBox -> {
            ToDoBoxHst toDoBoxHst = new ToDoBoxHst();
            toDoBoxHst.setSaveDt(LocalDateTime.now());
            toDoBoxHst.setTitle(toDoBox.getTitle());

            Long toDoBoxHstId = toDoBoxHstService.createToDoBoxHst(toDoBoxHst);

            toDoBox.getToDoElmList().forEach(toDoElm -> {
                ToDoElmHst toDoElmHst = new ToDoElmHst();
                toDoElmHst.setToDoBoxHst(toDoBoxHst);
                toDoElmHst.setContent(toDoElm.getContent());

                toDoElmHstService.createToElmHst(toDoElmHst);
            });
            System.out.println("toDoBoxHstID: " + toDoBoxHstId);
        });

    }

    // 초, 분, 시, 일, 월, 요일
    @Scheduled(cron="0 0 05 * * ?")
    public void resetToDo() {

    }
}
