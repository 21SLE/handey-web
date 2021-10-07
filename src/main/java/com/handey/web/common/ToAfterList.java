package com.handey.web.common;

import com.handey.web.after.AfterService;
import com.handey.web.afterhistory.AfterHistoryService;
import com.handey.web.weekly.WeeklyService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//clear가 1되면 바로 afterList로 이동하도록
@Component
public class ToAfterList {
    final private AfterService afterService;
    final private AfterHistoryService afterHistoryService;

    public ToAfterList(AfterService afterService, AfterHistoryService afterHistoryService) {
        this.afterService = afterService;
        this.afterHistoryService = afterHistoryService;
    }

    @Transactional
    public void moveToAfter(Long id, boolean clear){  //clear된 건 바로 after로 이동

        afterService.getAfterBoxListByUserId(id).forEach(afterBox -> {
            // history로 복사
            afterService.createAfterHistory(afterBox.getMember(), afterBox);
        });
    }
}
