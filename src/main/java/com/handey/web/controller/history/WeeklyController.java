package com.handey.web.controller.history;

import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WeeklyController {
    private final WeeklyService weeklyService;

    @Autowired
    public WeeklyController(WeeklyService weeklyService) {
        this.weeklyService = weeklyService;
    }

    @GetMapping("/weeklyBoxList")
    public List<WeeklyBox> getWeeklyBoxList() {
        return weeklyService.getWeeklyBoxList();
    }

    @GetMapping("/user/{userId}/weeklyBoxList")
    public List<WeeklyBox> getWeeklyBoxListByUserId(@PathVariable Long userId) {
        return weeklyService.getWeeklyBoxListByUserId(userId);
    }

    @PostMapping("/user/{userId}/weeklyBox")
    public Long createWeeklyBoxObj(@PathVariable Long userId) {
        return weeklyService.createWeeklyBoxObj(userId);
    }

    @PutMapping("/weeklyBox/{weeklyId}")
    public boolean updateWeeklyTitle(@PathVariable Long weeklyId, @RequestBody WeeklyParam title) {
        return weeklyService.updateWeeklyTitle(weeklyId, title);
    }

    @PatchMapping("/weeklyBox/{weeklyId}")
    public boolean updateWeeklyClear(@PathVariable Long weeklyId) {
        return weeklyService.updateWeeklyClear(weeklyId);
    }


    @DeleteMapping("/weeklyBox/{weeklyId}")
    public boolean deleteWeekly(@PathVariable Long weeklyId) {
        weeklyService.deleteWeekly(weeklyId);
        return true;
    }

}
