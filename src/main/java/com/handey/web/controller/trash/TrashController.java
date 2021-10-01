package com.handey.web.controller.trash;

import com.handey.web.common.exception.TrashNoDataFoundException;
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
     * 회원 휴지통 조회
     */
    @GetMapping("/user/{userId}/trashBoxList")
    public List<TrashBox> getTrashBoxListByUserId(@PathVariable Long userId) {
        return trashBoxService.getTrashBoxListByUserId(userId);
    }

    /**
     * 휴지통에서 삭제
     */
    @DeleteMapping("/user/trashBox/{trashBoxId}")
    public boolean deleteTrashBox(@PathVariable Long trashBoxId) {
        trashBoxService.deleteTrashBox(trashBoxId);
        return true;
    }

    /**
     * 휴지통 -> 투두 복구
     */
    @PutMapping("/user/{userId}/trashBox/{trashBoxId}")
    public boolean restoreFromTrashToToDo(@PathVariable Long userId, @PathVariable Long trashBoxId) {
        toDoBoxService.restoreToDo(userId, trashBoxService.getOneTrashBox(trashBoxId).orElseThrow(TrashNoDataFoundException::new));
        trashBoxService.deleteTrashBox(trashBoxId);
        return true;
    }

    /**
     * Only for test
     */
    @PostMapping("/user/{userId}/trash")
    public boolean createTrash(@PathVariable Long userId, @RequestBody TrashBox trashBox) {
        trashBoxService.createTrashBoxWithElms(userId, trashBox);
        return true;
    }
}
