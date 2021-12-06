package com.handey.web.weekly;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeeklyElmController {
    private final WeeklyElmService weeklyElmService;
    private final ResponseService responseService;

    @Autowired
    public WeeklyElmController(WeeklyElmService weeklyElmService, ResponseService responseService) {
        this.weeklyElmService = weeklyElmService;
        this.responseService = responseService;
    }

    @PostMapping("/user/weeklyBox/{weeklyId}")
    public SingleResponse<Long> createWeeklyElmObj(@PathVariable Long weeklyId) {
        return responseService.returnSingleResponse(weeklyElmService.createWeeklyElmObj(weeklyId));
    }

    @PutMapping("/user/weeklyElm/{weeklyElmId}")
    public Response updateWeeklyElmContent(@PathVariable Long weeklyElmId, @RequestBody WeeklyElmParam param) {
        weeklyElmService.updateWeeklyElmContent(weeklyElmId,param);
        return responseService.returnSuccessResponse();
    }

    @PatchMapping("/user/weeklyElm/{weeklyElmId}")
    public Response updateWeeklyElmContent(@PathVariable Long weeklyElmId) {
        weeklyElmService.updateWeeklyElmCompleted(weeklyElmId);
        return responseService.returnSuccessResponse();
    }

    @DeleteMapping("/user/weeklyElm/{weeklyElmId}")
    public Response deleteWeeklyElm(@PathVariable Long weeklyElmId) {
        weeklyElmService.deleteWeeklyElm(weeklyElmId);
        return responseService.returnSuccessResponse();
    }
}
