package com.handey.web.common.reset;

//afterbox에 있던 일을 다시 weekly로 옮길 때 사용.

import com.handey.web.after.AfterService;
import com.handey.web.weekly.WeeklyService;

public class ToWeeklyList {
    private final AfterService afterService;
    private final WeeklyService weeklyService;

    public ToWeeklyList(AfterService afterService, WeeklyService weeklyService) {
        this.afterService = afterService;
        this.weeklyService = weeklyService;
    }
}
