package com.handey.web.controller.history;

import com.handey.web.domain.history.AfterHistory;
import com.handey.web.service.AfterHistoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("history/afterList/{afterId}")
    public boolean deleteAfter(@PathVariable Long afterId) {
        afterHistoryService.deleteAfter(afterId);
        return true;
    }
}
