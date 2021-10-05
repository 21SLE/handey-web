package com.handey.web.common;

import com.handey.web.trash.TrashBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class TrashReset {
    private final TrashBoxService trashBoxService;

    @Autowired
    public TrashReset(TrashBoxService trashBoxService) {
        this.trashBoxService = trashBoxService;
    }

    // 매일 00시 정각에 휴지통에서 일주인된 투두들 삭제
    @Scheduled(cron="0 0 0 * * *")
    @Transactional
    public void resetTrash() {
        deleteTrash();
    }

    @Transactional
    public void deleteTrash() {
        trashBoxService.deleteTrashBoxListByDate(LocalDate.now());
    }
}
