package com.handey.web.schedule;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.member.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final MemberService memberService;

    public ScheduleController(ScheduleService scheduleService, MemberService memberService) {
        this.scheduleService = scheduleService;
        this.memberService = memberService;
    }

    /**
     * 회원별 스케줄 리스트 조회
     */
    @GetMapping("/user/{userId}/schedules")
    public List<Schedule> getScheduleListByUserId(@PathVariable Long userId) {
        return scheduleService.getScheduleListByUserId(userId);
    }

    /**
     * 회원별 스케줄 생성
     */
    @PostMapping("/user/{userId}/schedule")
    public Long createSchedule(@PathVariable Long userId, @RequestBody ScheduleParam param) {
        Schedule schedule = new Schedule();
        schedule.setContent(param.getContent());
        schedule.setStartDt(param.getStartDt());
        schedule.setEndDt(param.getEndDt());
        schedule.setMember(memberService.findByUserId(userId).orElseThrow(MemberNoDataFoundException::new));
        scheduleService.createSchedule(schedule);
        return schedule.getId();
    }

    /**
     * 스케줄 수정
     */
    @PutMapping("/user/schedule/{scheduleId}")
    public boolean modifySchedule(@PathVariable Long scheduleId, @RequestBody ScheduleParam param) {
        System.out.println(param.getContent());
        return scheduleService.modifySchedule(scheduleId, param);
    }


    /**
     * 스케줄 삭제
     */
    @DeleteMapping("/user/schedule/{scheduleId}")
    public boolean deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return true;
    }

}
