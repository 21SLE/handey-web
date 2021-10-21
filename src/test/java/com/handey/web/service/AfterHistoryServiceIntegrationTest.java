package com.handey.web.service;

import com.handey.web.afterhistory.AfterHistoryRepository;
import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.common.Reset.AfterReset;
import com.handey.web.member.Member;
import com.handey.web.member.MemberService;
import com.handey.web.weekly.WeeklyElmService;
import com.handey.web.weekly.WeeklyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class AfterHistoryServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    WeeklyService weeklyService;
    @Autowired
    WeeklyElmService weeklyElmService;
    @Autowired
    AfterHistoryService afterHistoryService;
    @Autowired
    AfterHistoryRepository afterHistoryRepository;
    @Autowired
    AfterReset afterReset;

    /**
     * 멤버 -> 위클리목록(위클리+ELM) -> UserInfo -> AfterReset -> AfterHistory 생성
     */
    @Test
    void createAfterList() {
        Member member = new Member();
        member.setEmail("tbdpapdl@naver.com");
        member.setPassword("dlwls97611!");
        member.setUsername("노이진");

        //Long userId = memberService.join(member);
    }

}
