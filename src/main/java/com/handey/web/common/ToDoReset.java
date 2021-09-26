package com.handey.web.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ToDoReset {

    //5초 마다 실행
    @Scheduled(fixedDelay=5000)
    public void testScheduler(){
        System.out.println("스케줄링 테스트");
    }

    // 초, 분, 시, 일, 월, 요일
    @Scheduled(cron="0 0 05 * * ?")
    public void resetToDo() {

    }
}
