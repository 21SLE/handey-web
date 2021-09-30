package com.handey.web.controller.trash;

import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.domain.trash.TrashBox;
import com.handey.web.service.ToDoBoxService;
import com.handey.web.service.TrashBoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrashController {
    private final ToDoBoxService toDoBoxService;
    private final TrashBoxService trashBoxService;

    public TrashController(ToDoBoxService toDoBoxService, TrashBoxService trashBoxService) {
        this.toDoBoxService = toDoBoxService;
        this.trashBoxService = trashBoxService;
    }

    /**
     * 휴지통 전체 조회
     */
    @GetMapping("/trashBoxList")
    public List<TrashBox> getTrashBoxList() {
        return trashBoxService.getTrashBoxList();
    }

    /**
     * 휴지통에서 삭제
     */
    @DeleteMapping("/trashBox/{trashBoxId}")
    public boolean deleteTrashBox(@PathVariable Long trashBoxId) {
        trashBoxService.deleteTrashBox(trashBoxId);
        return true;
    }

    /**
     * 휴지통 -> 투두 복구
     */
    @PutMapping("/trashBox/{trashBoxId}")
    public boolean restoreFromTrashToToDo(@PathVariable Long trashBoxId) {
        toDoBoxService.restoreToDo(trashBoxService.getOneTrashBox(trashBoxId).orElseThrow(WeeklyNoDataFoundException::new));
        trashBoxService.deleteTrashBox(trashBoxId);
        return true;
    }

    /**
     * Only for test
     */
    @PostMapping("/trash")
    public boolean createTrash(@RequestBody TrashBox trashBox) {
        trashBoxService.createTrashBoxWithElms(trashBox);
        return true;
    }
}
