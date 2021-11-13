package com.handey.web.common.reset;

import com.handey.web.member.Member;
import com.handey.web.todohistory.ToDoBoxHstService;
import com.handey.web.todo.ToDoBoxService;
import com.handey.web.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class ToDoReset {
    private final ToDoBoxService toDoBoxService;
    private final ToDoBoxHstService toDoBoxHstService;
    private final UserInfoService userInfoService;

    @Autowired
    public ToDoReset(ToDoBoxService toDoBoxService, ToDoBoxHstService toDoBoxHstService, UserInfoService userInfoService) {
        this.toDoBoxService = toDoBoxService;
        this.toDoBoxHstService = toDoBoxHstService;
        this.userInfoService = userInfoService;
    }

    //user_info 테이블에서 reset_time == 0 인 user 것들만 todoBox 테이블에서 resetToDo함수 실행
    // 초, 분, 시, 일, 월, 요일
    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void resetToDoAt00() {
        resetToDo("0");
    }

    @Scheduled(cron="0 0 01 * * *")
    @Transactional
    public void resetToDoAt01() {
        resetToDo("1");
    }

    @Scheduled(cron="0 0 02 * * *")
    @Transactional
    public void resetToDoAt02() {
        resetToDo("2");
    }

    @Scheduled(cron="0 0 03 * * *")
    @Transactional
    public void resetToDoAt03() {
        resetToDo("3");
    }

    @Scheduled(cron="0 0 04 * * *")
    @Transactional
    public void resetToDoAt04() {
        resetToDo("4");
    }

    @Scheduled(cron="0 0 05 * * *")
    @Transactional
    public void resetToDoAt05() {
        resetToDo("5");
    }

    @Scheduled(cron="0 0 06 * * *")
    @Transactional
    public void resetToDoAt06() {
        resetToDo("6");
    }

//    @Scheduled(fixedDelay=1000000)
//    @Transactional
//    public void resetToDoTest() {
//        System.out.println("resetToDo 테스트");
//        resetToDo("1");
//    }

    @Transactional
    public void resetToDo(String resetTime){
        // 0시엔 user_info 테이블에서 reset_time == 0 인 user 리스트 조회
        List<Member> memberList = userInfoService.getUserListByResetTime(resetTime);

        memberList.forEach(member ->
                toDoBoxService.getToDoBoxListByUserId(member.getId()).forEach(toDoBox -> {
                    // history로 복사
                    boolean doesToDoBoxNeedToBeDeleted = toDoBoxHstService.createToDoBoxHst(toDoBox.getMember(), toDoBox);

                    // todoelm 모두 completed이면 todobox 삭제
                    if (doesToDoBoxNeedToBeDeleted) {
                        toDoBoxService.deleteToDoBox(toDoBox.getId());
                    }
                }));
    }
}
