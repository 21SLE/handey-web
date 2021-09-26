package com.handey.web.repository.history;

import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.history.WeeklyElm;

import java.util.List;
import java.util.Optional;

public interface WeeklyElmRepository {
    WeeklyElm save(WeeklyElm weeklyElm, WeeklyBox weeklyBox);
    Optional<WeeklyElm> findById(Long id);
    List<WeeklyElm> findByWeeklyId(Long weeklyBoxId);
    List<WeeklyElm> findAll();
    void deleteById(Long id);
}
