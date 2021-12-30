package com.handey.web.weekly;

import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WeeklyController {
    private final WeeklyService weeklyService;
    private final ResponseService responseService;

    @Autowired
    public WeeklyController(WeeklyService weeklyService, ResponseService responseService) {
        this.weeklyService = weeklyService;
        this.responseService = responseService;
    }

    @GetMapping("/weeklyBoxList")
    public ListResponse<WeeklyBox> getWeeklyBoxList() {
        return responseService.returnListResponse(weeklyService.getWeeklyBoxList());
    }

    @GetMapping("/user/{userId}/weeklyBoxList")
    public ListResponse<WeeklyBox> getWeeklyBoxListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(weeklyService.getWeeklyBoxListByUserId(userId));
    }

    @PostMapping("/user/{userId}/weeklyBox")
    public SingleResponse<Optional<WeeklyBox>> createWeeklyBoxObj(@PathVariable Long userId) {
        return responseService.returnSingleResponse(weeklyService.createWeeklyBoxObj(userId));
    }

    @PutMapping("/user/weeklyBox/{weeklyId}")
    public Response updateWeeklyTitle(@PathVariable Long weeklyId, @RequestBody WeeklyParam title) {
        weeklyService.updateWeeklyTitle(weeklyId, title);
        return responseService.returnSuccessResponse();
    }

//    @PatchMapping("/user/weeklyBox/{weeklyId}")
//    public Response updateWeeklyClear(@PathVariable Long weeklyId) {
//        weeklyService.updateWeeklyClear(weeklyId);
//        return responseService.returnSuccessResponse();
//    }


    @DeleteMapping("/user/weeklyBox/{weeklyId}")
    public Response deleteWeekly(@PathVariable Long weeklyId) {
        weeklyService.deleteWeekly(weeklyId);
        return responseService.returnSuccessResponse();
    }

}
