package com.handey.web.common;

import com.handey.web.member.Member;
import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.userinfo.UserInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AfterReset {
    private final AfterHistoryService afterHistoryService;
    private final UserInfoService userInfoService;

    public AfterReset(AfterHistoryService afterHistoryService, UserInfoService userInfoService) {
        this.afterHistoryService = afterHistoryService;
        this.userInfoService = userInfoService;
    }

    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void resetAfterAt00() {
        resetAfter("0");
    }

    @Transactional
    public void resetAfter(String resetTime){
        // 0시엔 user_info 테이블에서 reset_time == 0 인 user 리스트 조회
        List<Member> memberList = userInfoService.getUserListByResetTime(resetTime);

    }
}