package com.handey.web.trash;

import com.handey.web.common.exception.TrashNoDataFoundException;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.todo.ToDoBoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrashController {
    private final ToDoBoxService toDoBoxService;
    private final TrashBoxService trashBoxService;
    private final ResponseService responseService;

    public TrashController(ToDoBoxService toDoBoxService, TrashBoxService trashBoxService, ResponseService responseService) {
        this.toDoBoxService = toDoBoxService;
        this.trashBoxService = trashBoxService;
        this.responseService = responseService;
    }

    /**
     * 휴지통 전체 조회
     */
    @GetMapping("/trashBoxList")
    public ListResponse<TrashBox> getTrashBoxList() {
        return responseService.returnListResponse(trashBoxService.getTrashBoxList());
    }

    /**
     * 회원 휴지통 조회
     */
    @GetMapping("/user/{userId}/trashBoxList")
    public ListResponse<TrashBox> getTrashBoxListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(trashBoxService.getTrashBoxListByUserId(userId));
    }

    /**
     * 휴지통에서 삭제
     */
    @DeleteMapping("/user/trashBox/{trashBoxId}")
    public Response deleteTrashBox(@PathVariable Long trashBoxId) {
        trashBoxService.deleteTrashBox(trashBoxId);
        return responseService.returnSuccessResponse();
    }

    /**
     * 휴지통 -> 투두 복구
     */
    @PutMapping("/user/{userId}/trashBox/{trashBoxId}")
    public Response restoreFromTrashToToDo(@PathVariable Long userId, @PathVariable Long trashBoxId) {
        toDoBoxService.restoreToDo(userId, trashBoxService.getOneTrashBox(trashBoxId).orElseThrow(TrashNoDataFoundException::new));
        trashBoxService.deleteTrashBox(trashBoxId);
        return responseService.returnSuccessResponse();
    }

    /**
     * Only for test
     */
    @PostMapping("/user/{userId}/trash")
    public Response createTrash(@PathVariable Long userId, @RequestBody TrashBox trashBox) {
        trashBoxService.createTrashBoxWithElms(userId, trashBox);
        return responseService.returnSuccessResponse();
    }
}
