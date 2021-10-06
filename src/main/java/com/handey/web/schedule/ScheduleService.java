package com.handey.web.schedule;

import com.handey.web.common.exception.ScheduleNoDataFoundException;
import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.todo.ToDoBox;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * 일정 생성
     */
    public Long createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    /**
     * 해당 회원의 일정 조회
     */
    public List<Schedule> getScheduleListByUserId(Long userId) {
        return scheduleRepository.findAllByUserId(userId);
    }

    /**
     * 일정 단건 조회
     */
    public Optional<Schedule> findOneCalendar(Long id) {
        return scheduleRepository.findById(id);
    }

    /**
     * 일정 수정
     */
    public boolean modifySchedule(Long scheduleId, ScheduleParam param) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNoDataFoundException::new);
        schedule.updateSchedule(param);
        System.out.println(schedule.getContent());
        return true;
    }

    /**
     * 일정 삭제
     */
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNoDataFoundException::new);
        scheduleRepository.deleteById(scheduleId);
    }
}
