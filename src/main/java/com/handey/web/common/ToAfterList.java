package com.handey.web.common;

import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//clear가 1되면 바로 afterList로 이동하도록
@Component
public class ToAfterList {
    final private WeeklyService weeklyService;
    final private AfterHistoryService afterHistoryService;
    public ToAfterList(WeeklyService weeklyService, AfterHistoryService afterHistoryService) {
        this.weeklyService = weeklyService;
        this.afterHistoryService = afterHistoryService;
    }

    @Transactional
    public void moveToAfter(Long id, boolean clear){  //clear된 건 바로 after로 이동

        weeklyService.getWeeklyBoxListByUserId(id).forEach(weeklyBox -> {
            // history로 복사
            weeklyService.createAfter(weeklyBox.getMember(), weeklyBox);
        });
    }
}
