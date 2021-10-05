package com.handey.web.weekly;

import java.util.List;
import java.util.Optional;

public interface WeeklyElmRepository {
    WeeklyElm save(WeeklyElm weeklyElm, WeeklyBox weeklyBox);
    Optional<WeeklyElm> findById(Long id);
    List<WeeklyElm> findByWeeklyId(Long weeklyBoxId);
    List<WeeklyElm> findAll();
    void deleteById(Long id);
}
