package com.handey.web.controller.history;

import com.handey.web.domain.history.AfterHistory;
import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.service.AfterHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AfterHistoryController {
    private final AfterHistoryService afterHistoryService;

    public AfterHistoryController(AfterHistoryService afterHistoryService) {
        this.afterHistoryService = afterHistoryService;
    }

    @GetMapping("/history/afterList")
    public List<AfterHistory> getAfterList() {
        return afterHistoryService.getAfterList();
    }

    @GetMapping("/history/after/date")
    public List<AfterHistory> getAfterListByDate(@RequestBody ToDoHstParam param) {
        return afterHistoryService.getAfterListByDate(param.getSearchDt());
    }

    @GetMapping("/user/{userId}/history/afterList")
    public List<AfterHistory> getAfterListByUserId(@PathVariable Long userId) {
        return afterHistoryService.getAfterListByUserId(userId);
    }

    @DeleteMapping("/user/{userId}/history/afterList/{afterId}")
    public boolean deleteAfter(@PathVariable Long afterId) {
        afterHistoryService.deleteAfter(afterId);
        return true;
    }
}
