package com.handey.web.controller.history;

import com.handey.web.controller.home.ToDoBoxParam;
import com.handey.web.domain.home.WeeklyBox;
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

    @PostMapping("/weeklyBox")
    public Long createWeeklyBoxObj() {
        return weeklyService.createWeeklyBoxObj();
    }

    @PutMapping("/weeklyBox/{weeklyId}")
    public boolean updateWeeklyTitle(@PathVariable Long weeklyId, @RequestBody WeeklyParam param) {
        return weeklyService.updateWeeklyTitle(weeklyId, param);
    }

}
