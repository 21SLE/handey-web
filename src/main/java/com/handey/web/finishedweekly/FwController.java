package com.handey.web.finishedweekly;

import com.handey.web.common.exception.FwNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.todo.ToDoBoxParam;
import com.handey.web.todo.ToDoElmParam;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import com.handey.web.weekly.WeeklyElmService;
import com.handey.web.weekly.WeeklyService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class FwController {
    private final FwService fwService;
    private final WeeklyService weeklyService;
    private final WeeklyElmService weeklyElmService;
    private final ResponseService responseService;

    public FwController(FwService fwService, WeeklyService weeklyService, WeeklyElmService weeklyElmService, ResponseService responseService) {
        this.fwService = fwService;
        this.weeklyService = weeklyService;
        this.weeklyElmService = weeklyElmService;
        this.responseService = responseService;
    }

    /**
     * 회원 Fw조회
     */
    @GetMapping("/user/{userId}/fw")
    public ListResponse<FwBox> getFwBoxListByUserIdAndDate(@PathVariable Long userId, @RequestParam String dt) {
        LocalDate searchDt = LocalDate.parse(dt, DateTimeFormatter.ISO_DATE);
        return responseService.returnListResponse(fwService.getFwBoxListByUserIdAndDate(userId, searchDt));
    }

    /**
     * 위클리 -> Fw
     */
    @PostMapping("/user/{userId}/fwelm/{weeklyElmId}")
    public Response addFwElm(@PathVariable Long userId, @PathVariable Long weeklyElmId) {
        WeeklyElm weeklyElm = weeklyElmService.findOneWeeklyElm(weeklyElmId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyElm.updateCompleted(true);
        WeeklyBox weeklyBox = weeklyService.findOneWeeklyBox(weeklyElm.getWeeklyBox().getId()).orElseThrow(WeeklyNoDataFoundException::new);
        LocalDate searchDt = LocalDate.now();
        fwService.handleAddingFwElm(userId, searchDt, weeklyBox, weeklyElm);
        return responseService.returnSuccessResponse();
    }

    /**
     * Fw -> 위클리
     */
    @PutMapping("/user/{userId}/fwbox/{weeklyBoxId}/fwelm/{weeklyElmId}")
    public Response restoreFwElmToWeekly(@PathVariable Long userId, @PathVariable Long weeklyBoxId, @PathVariable Long weeklyElmId) {
        FwBox fwBox = fwService.finOneFwBoxByWeeklyBoxIdAndDate(weeklyBoxId, LocalDate.now()).orElseThrow(FwNoDataFoundException::new);
        FwElm fwElm = fwService.finOneFwElmByFwBoxIdAndWeeklyElmId(fwBox.getId(), weeklyElmId).orElseThrow(FwNoDataFoundException::new);

        fwService.handleRestoringFwElm(userId, fwBox, fwElm);
        return responseService.returnSuccessResponse();
    }

    /**
     * fw 타이틀 수정
     */
    @PutMapping("/user/fwbox/{weeklyBoxId}")
    public Response updateFwBoxTitle(@PathVariable Long weeklyBoxId, @RequestBody FwBoxParam param) {
        if(fwService.updateFwBoxTitle(weeklyBoxId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }

    /**
     * fwelm 내용 수정
     */
    @PutMapping("/user/fwbox/{weeklyBoxId}/fwelm/{weeklyElmId}")
    public Response updateFwElmContent(@PathVariable Long weeklyBoxId, @PathVariable Long weeklyElmId, @RequestBody FwElmParam param) {
        if(fwService.updateFwElmContent(weeklyBoxId, weeklyElmId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }
}
