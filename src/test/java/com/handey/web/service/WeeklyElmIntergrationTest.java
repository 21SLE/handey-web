package com.handey.web.service;

import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.history.WeeklyElm;
import com.handey.web.repository.history.WeeklyElmRepository;
import com.handey.web.repository.history.WeeklyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

public class WeeklyElmIntergrationTest {
    @Autowired
    WeeklyElmService weeklyElmService;
    @Autowired
    WeeklyElmRepository weeklyElmRepository;

    @Test
    @Commit
    void createWeeklyElmBox() {
        //given
        WeeklyElm weeklyElm1 = new WeeklyElm();
        weeklyElm1.setSubtitle("sprint1");

        WeeklyElm weeklyElm2 = new WeeklyElm();
        weeklyElm2.setSubtitle("sprint2");

        WeeklyElm weeklyElm3 = new WeeklyElm();
        weeklyElm3.setSubtitle("sprint3");

        //when
        Long saveId1 = weeklyElmService.createWeeklyElm(,weeklyElm1);
        Long saveId2 = weeklyElmService.createWeeklyElm(,weeklyElm2);
        Long saveId3 = weeklyElmService.createWeeklyElm(,weeklyElm3);

        //then
        WeeklyElm findWeeklyElm1 = weeklyElmService.findOneWeeklyElm(saveId1).get();
        WeeklyElm findWeeklyElm2 = weeklyElmService.findOneWeeklyElm(saveId2).get();
        WeeklyElm findWeeklyElm3 = weeklyElmService.findOneWeeklyElm(saveId3).get();
        assertThat(findWeeklyElm1.getSubtitle()).isEqualTo(findWeeklyElm1.getSubtitle());
        assertThat(findWeeklyElm2.getSubtitle()).isEqualTo(findWeeklyElm2.getSubtitle());
        assertThat(findWeeklyElm3.getSubtitle()).isEqualTo(findWeeklyElm3.getSubtitle());

    }

    @Test
    void getWeeklyElmList() {
    }

    @Test
    void findOneWeeklyElm() {
    }
}
}
