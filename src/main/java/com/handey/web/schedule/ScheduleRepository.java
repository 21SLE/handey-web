package com.handey.web.schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    Optional<Schedule> findById(Long id);
    List<Schedule> findAll();
    List<Schedule> findAllByUserId(Long userId);
    void deleteById(Long id);

}
