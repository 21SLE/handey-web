package com.handey.web.controller.history;

import com.handey.web.service.WeeklyElmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeeklyElmController {
    private final WeeklyElmService weeklyElmService;

    @Autowired
    public WeeklyElmController(WeeklyElmService weeklyElmService) {
        this.weeklyElmService = weeklyElmService;
    }

    @PostMapping("/weeklyBox/{weeklyBoxId}")
    public Long createWeeklyElmObj(@PathVariable Long weeklyId) {
        return weeklyElmService.createWeeklyElmObj(weeklyId);
    }

    @PutMapping("/weeklyElm/{weeklyElmId}")
    public boolean updateWeeklyElmContent(@PathVariable Long weeklyElmId, @RequestBody WeeklyElmParam param) {
        return weeklyElmService.updateWeeklyElmContent(weeklyElmId);
    }

    @DeleteMapping("/weeklyElm/{weeklyElmId}")
    public void deleteWeeklyElm(@PathVariable Long weeklyElmId) {
        weeklyElmService.deleteWeeklyElm(weeklyElmId);
    }

}
