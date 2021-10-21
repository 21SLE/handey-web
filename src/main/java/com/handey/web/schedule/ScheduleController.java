package com.handey.web.schedule;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.response.ListResponse;
import com.handey.web.common.response.Response;
import com.handey.web.common.response.ResponseService;
import com.handey.web.common.response.SingleResponse;
import com.handey.web.member.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final MemberService memberService;
    private final ResponseService responseService;

    public ScheduleController(ScheduleService scheduleService, MemberService memberService, ResponseService responseService) {
        this.scheduleService = scheduleService;
        this.memberService = memberService;
        this.responseService = responseService;
    }

    /**
     * 회원별 스케줄 리스트 조회
     */
    @GetMapping("/user/{userId}/schedules")
    public ListResponse<Schedule> getScheduleListByUserId(@PathVariable Long userId) {
        return responseService.returnListResponse(scheduleService.getScheduleListByUserId(userId));
    }

    /**
     * 회원별 스케줄 생성
     */
    @PostMapping("/user/{userId}/schedule")
    public SingleResponse<Long> createSchedule(@PathVariable Long userId, @RequestBody ScheduleParam param) {
        Schedule schedule = new Schedule();
        schedule.setContent(param.getContent());
        schedule.setStartDt(param.getStartDt());
        schedule.setEndDt(param.getEndDt());
        schedule.setMember(memberService.findByUserId(userId).orElseThrow(MemberNoDataFoundException::new));
        scheduleService.createSchedule(schedule);
        return responseService.returnSingleResponse(schedule.getId());
    }

    /**
     * 스케줄 수정
     */
    @PutMapping("/user/schedule/{scheduleId}")
    public Response modifySchedule(@PathVariable Long scheduleId, @RequestBody ScheduleParam param) {
        System.out.println(param.getContent());
        if(scheduleService.modifySchedule(scheduleId, param))
            return responseService.returnSuccessResponse();
        else
            return responseService.returnFailResponse();
    }


    /**
     * 스케줄 삭제
     */
    @DeleteMapping("/user/schedule/{scheduleId}")
    public Response deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return responseService.returnSuccessResponse();
    }

}
