package com.handey.web.controller.home;

import com.handey.web.domain.home.WeeklyBox;
import com.handey.web.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
