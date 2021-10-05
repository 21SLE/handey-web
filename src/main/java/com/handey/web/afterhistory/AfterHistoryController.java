package com.handey.web.afterhistory;

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

    @GetMapping("/user/{userId}/history/after/date")
    public List<AfterHistory> getAfterListByDate(@PathVariable Long userId, @RequestBody AfterHistoryParam param) {
        return afterHistoryService.getAfterListByDate(userId, param.getSearchDt());
    }

    @GetMapping("/user/{userId}/history/afterList")
    public List<AfterHistory> getAfterListByUserId(@PathVariable Long userId) {
        return afterHistoryService.getAfterListByUserId(userId);
    }

    @DeleteMapping("/user/{userId}/history/afterList/{afterId}")
    public boolean deleteAfter(@PathVariable Long userId, @PathVariable Long afterId) {
        afterHistoryService.deleteAfter(afterId);
        return true;
    }
}
