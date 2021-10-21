package com.handey.web.afterhistory;

import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AfterHistoryController {
    private final AfterHistoryService afterHistoryService;
    private final ResponseService responseService;

    public AfterHistoryController(AfterHistoryService afterHistoryService, ResponseService responseService) {
        this.afterHistoryService = afterHistoryService;
        this.responseService = responseService;
    }

    /**
     *  afterhistory 전체 조회
     */
    @GetMapping("/history/afterList")
    public ListResponse<AfterHistory> getAfterList() {
        return responseService.returnListResponse(afterHistoryService.getAfterList());
    }

    @GetMapping("/user/{userId}/history/after/date")
    public ListResponse<AfterHistory> getAfterListByDate(@PathVariable Long userId, @RequestBody AfterHistoryParam param) {
        return responseService.returnListResponse(afterHistoryService.getAfterListByDate(userId, param.getSearchDt()));
    }

    @GetMapping("/user/{userId}/history/afterList")
    public ListResponse<AfterHistory> getAfterListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(afterHistoryService.getAfterListByUserId(userId));
    }

    @DeleteMapping("/user/{userId}/history/afterList/{afterId}")
    public Response deleteAfter(@PathVariable Long userId, @PathVariable Long afterId) {
        afterHistoryService.deleteAfter(afterId);
        return responseService.returnSuccessResponse();
    }
}
