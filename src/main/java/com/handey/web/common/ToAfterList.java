package com.handey.web.common;

import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.weekly.WeeklyService;
import org.springframework.stereotype.Component;

//clear가 1되면 바로 afterList로 이동하도록
@Component
public class ToAfterList {
    final private WeeklyService weeklyService;
    final private AfterHistoryService afterHistoryService;

    public ToAfterList(WeeklyService weeklyService, AfterHistoryService afterHistoryService) {
        this.weeklyService = weeklyService;
        this.afterHistoryService = afterHistoryService;
    }
}
