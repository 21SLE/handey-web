package com.handey.web.common.reset;

import com.handey.web.member.Member;
import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.userinfo.UserInfoService;
import com.handey.web.weekly.WeeklyElmService;
import com.handey.web.weekly.WeeklyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AfterReset {
    private final WeeklyService weeklyService;
    private final WeeklyElmService weeklyElmService;
    private final AfterHistoryService afterHistoryService;
    private final UserInfoService userInfoService;

    public AfterReset(WeeklyService weeklyService, WeeklyElmService weeklyElmService, AfterHistoryService afterHistoryService, UserInfoService userInfoService) {
        this.weeklyService = weeklyService;
        this.weeklyElmService = weeklyElmService;
        this.afterHistoryService = afterHistoryService;
        this.userInfoService = userInfoService;
    }

    // 초, 분, 시, 일, 월, 요일
    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void resetAfterAt00() {
        resetAfter("0");
    }


    @Scheduled(cron="0 0 01 * * *")
    @Transactional
    public void resetToDoAt01() {
        resetAfter("1");
    }

    @Scheduled(cron="0 0 02 * * *")
    @Transactional
    public void resetToDoAt02() {
        resetAfter("2");
    }

    @Scheduled(cron="0 0 03 * * *")
    @Transactional
    public void resetToDoAt03() {
        resetAfter("3");
    }

    @Scheduled(cron="0 0 04 * * *")
    @Transactional
    public void resetToDoAt04() {
        resetAfter("4");
    }

    @Scheduled(cron="0 0 05 * * *")
    @Transactional
    public void resetToDoAt05() {
        resetAfter("5");
    }

    @Scheduled(cron="0 0 06 * * *")
    @Transactional
    public void resetToDoAt06() {
        resetAfter("6");
    }

//    @Scheduled(fixedDelay=1000000)
//    @Transactional
//    public void resetAfterTest() {
//        System.out.println("resetAfter 테스트");
//        resetToDoAt01();
//    }

    @Transactional
    public void resetAfter(String resetTime){
        // 0시엔 user_info 테이블에서 reset_time == 0 인 user 리스트 조회
        List<Member> memberList = userInfoService.getUserListByResetTime(resetTime);

        memberList.forEach(member ->
                weeklyService.getWeeklyBoxListByUserId(member.getId()).forEach(weeklyBox -> {
                    // history로 복사
                    afterHistoryService.createAfterHistory(weeklyBox.getMember(), weeklyBox);
                }
                ));

    }
}