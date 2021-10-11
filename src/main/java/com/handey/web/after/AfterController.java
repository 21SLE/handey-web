package com.handey.web.after;

import com.handey.web.afterhistory.AfterHistory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AfterController {
    private final AfterService afterService;

    public AfterController(AfterService afterService) {
        this.afterService = afterService;
    }

    @GetMapping("/afterBoxList")
    public List<AfterBox> getAfterList() {
        return afterService.getAfterBoxList();
    }

    @GetMapping("/userId/{userId}/afterBoxList")
    public List<AfterBox> getAfterListByUserId(@PathVariable Long userId) {
        return afterService.getAfterBoxListByUserId(userId);
    }

    @PatchMapping("/afterBox/{afterId}")
    public boolean updateAfterClear(@PathVariable Long afterId) {
        return afterService.updateAfterClear(afterId);
    }

    @PatchMapping("/afterBox/{afterId}")
    public boolean updateSubtitleClear(@PathVariable Long afterId) {
        return afterService.updateAfterSubtitle(afterId);
    }


    @DeleteMapping("/afterBox/{afterId}")
    public boolean deleteAfterBox(@PathVariable Long afterId) {
        afterService.deleteAfterBox(afterId);
        return true;
    }
}
