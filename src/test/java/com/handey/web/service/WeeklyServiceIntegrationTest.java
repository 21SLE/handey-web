package com.handey.web.service;

import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.repository.history.WeeklyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WeeklyServiceIntegrationTest {

    @Autowired WeeklyService weeklyService;
    @Autowired
    WeeklyRepository weeklyRepository;

    @Test
    @Commit
    void createWeeklyBox() {
        //given
        WeeklyBox weeklyBox1 = new WeeklyBox();
        weeklyBox1.setTitle("sprint1");

        WeeklyBox weeklyBox2 = new WeeklyBox();
        weeklyBox2.setTitle("sprint2");

        WeeklyBox weeklyBox3 = new WeeklyBox();
        weeklyBox3.setTitle("sprint3");

        //when
        Long saveId1 = weeklyService.createWeeklyBox(weeklyBox1);
        Long saveId2 = weeklyService.createWeeklyBox(weeklyBox2);
        Long saveId3 = weeklyService.createWeeklyBox(weeklyBox3);

        //then
        WeeklyBox findWeekly1 = weeklyService.findOneWeeklyBox(saveId1).get();
        WeeklyBox findWeekly2 = weeklyService.findOneWeeklyBox(saveId2).get();
        WeeklyBox findWeekly3 = weeklyService.findOneWeeklyBox(saveId3).get();
        assertThat(findWeekly1.getTitle()).isEqualTo(findWeekly1.getTitle());
        assertThat(findWeekly2.getTitle()).isEqualTo(findWeekly2.getTitle());
        assertThat(findWeekly3.getTitle()).isEqualTo(findWeekly3.getTitle());

    }

    @Test
    void getWeeklyBoxList() {
    }

    @Test
    void findOneWeeklyBox() {
    }
}