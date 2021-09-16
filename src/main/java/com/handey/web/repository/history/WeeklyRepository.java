package com.handey.web.repository.history;

import com.handey.web.domain.history.WeeklyBox;

import java.util.List;
import java.util.Optional;

public interface WeeklyRepository {
    WeeklyBox save(WeeklyBox weeklyBox);
    Optional<WeeklyBox> findById(Long id);
    Optional<WeeklyBox> findByTitle(String title);
    List<WeeklyBox> findAll();
    void deleteById(Long id);
}